package Controller.LevelCreationPackage;

import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;
import View.GameBoard;
import View.Level;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LevelCreator {
    private List<Enemy> enemyList;

    public LevelCreator(){
        enemyList = new ArrayList<>();
    }

    //gets the level path and creates a game board according to to the txt lines of the level input.
    public Level decipherLevel(String levelPath, Player p){
        Level newLevel;
        List<String> rawLevelLines = readAllLines(levelPath);
        int boardLength = rawLevelLines.get(0).length();
        int boardWidth = rawLevelLines.size();
        Tile[][] layOut = createLayout(rawLevelLines,boardLength,boardWidth, p);
        GameBoard theGameBoard = new GameBoard(layOut);
         newLevel = new Level(theGameBoard,enemyList);
         return newLevel;
    }

    //creates a list represented as a string of each line in the text file.
    private List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch(IOException ex){
            System.out.println("something went wrong with reading the file. Please restart the program\n" + ex.getMessage() + "\n" + ex.getStackTrace());
        }
        return lines;
    }
    //creates the game board layout according to the received levelLines
    private Tile[][] createLayout(List<String> levelLines, int length, int width, Player p){
        Tile[][] layOut = new Tile[length][width];
        for (int j = 0; j < layOut[0].length; j++) {
            for (int i = 0; i < layOut.length; i++) {
                char toDecode;
                if(!levelLines.get(j).isEmpty())
                    toDecode = levelLines.get(j).charAt(i);
                else
                    return null;
                //determines what type of tile to create.
                Point tilePlacement = new Point(i,j);
                if(toDecode == '@') {
                    //Player case
                    //Inserts the player in the current position in the layout and sets the chosen player position.
                    p.setPosition(tilePlacement);
                    layOut[i][j] = p;
                } else if(toDecode == '#'){
                    //Wall Case
                    //creates a wall
                    layOut[i][j] = new WallTileFactory().getTile(toDecode, tilePlacement);

                }else if(toDecode == '.'){
                    //empty Tile Case
                    //creates an emptyTile
                    layOut[i][j] = new EmptyTileFactory().getTile(toDecode,tilePlacement);
                }
                else{
                    //enemy Case. adding the enemy to the list.
                    Enemy newEnemy = (Enemy) new EnemyFactory().getTile(toDecode,tilePlacement);
                    enemyList.add(newEnemy);
                    layOut[i][j] = newEnemy;
                }
            }
        }
        return layOut;
    }
}
