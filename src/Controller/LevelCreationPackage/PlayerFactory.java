package Controller.LevelCreationPackage;

import Model.TilePackage.Tile;
import Model.UnitPackage.PlayerPackage.*;

import java.awt.Point;

public class PlayerFactory implements AbstractTileFactory {
    //creates the player according to the input 'c'.
    @Override
    public Tile getTile(char c, Point position) {
        Player selectedPlayer;
        for (Warriors w: Warriors.values()) {
            if(w.getMenuPosition()==(Character.valueOf(c)-48)){
                selectedPlayer = new Warrior(position, w);
                return selectedPlayer;
            }
        }
        for (Mages m: Mages.values()) {
            if(m.getMenuPosition()==(Character.valueOf(c)-48)){
                selectedPlayer = new Mage(position, m);
                return selectedPlayer;
            }
        }
        for (Rogues r: Rogues.values()) {
            if(r.getMenuPosition()==(Character.valueOf(c)-48)){
                selectedPlayer = new Rogue(position, r);
                return selectedPlayer;
            }
        }
        for (Hunters h : Hunters.values()) {
            if(h.getMenuPosition()==(Character.valueOf(c)-48)){
                selectedPlayer = new Hunter(position, h);
                return selectedPlayer;
            }
        }
        return null;
    }
}
