package Model.UnitPackage;

import Model.TilePackage.EmptyTile;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;

public interface Visitor {
    default String visit(Enemy enemy) {
        return null; //Do nothing
    }
    default String visit(Player player) {
        return null; //Do nothing
    }
    String visit(EmptyTile emptyTile);
}
