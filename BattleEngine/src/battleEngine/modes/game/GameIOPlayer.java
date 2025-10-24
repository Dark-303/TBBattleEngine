package battleEngine.modes.game;

import battleEngine.modes.Attack;
import battleEngine.modes.PlayerData;

public class GameIOPlayer implements GameIO {
    private PlayerData playerData;
    private Attack primary;
    private Attack secondary;
    private Attack ultimate;    

    // Constructor
    public GameIOPlayer() {
        playerData = new PlayerData();
    }

    @Override
    public void updatePlayer() {
        // Implementation for updating player state
    }
}