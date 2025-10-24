package battleEngine.game.io;

import battleEngine.data.entities.PlayerData;
import battleEngine.data.entities.enemies.ScaledEnemy;

public interface GameIO {
    public default PlayerData updatePlayer() {
        return null;
    }

    public default ScaledEnemy updateEnemy() {
        return null;
    }
}
