package battleEngine.modes.game;

import battleEngine.modes.PlayerData;

public interface GameIO {
    public default PlayerData updatePlayer() {
        return null;
    }

    public default EnemyData updatePlayer() {
        return null;
    }
}
