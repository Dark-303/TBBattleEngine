package battleEngine.game.io;

import battleEngine.data.entities.PlayerData;
import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;

public class GameIOPlayer implements GameIO {
    private PlayerData playerData;
    private Attack primary;
    private Attack secondary;
    private Attack ultimate;
    private Armor armor;    

    // Constructor
    public GameIOPlayer() {
        primary = new Attack("Ten Thousand Shadow Fists", 60.00, 40.00, 0.70, 0.60, 1);
        secondary = new Attack("Dragon Palm", 50.00, 40.00, 0.40, 0.30, 1);
        ultimate = new Attack("Rose Thorns", 100.00, 90.00, 0.80, 0.5, 3);
        armor = new Armor("Tekketsuhana Armor", 100);
        playerData = new PlayerData(100, 7, armor, primary, secondary, ultimate, 1);
    }

    @Override
    public PlayerData updatePlayer() {
        return playerData;
    }
}