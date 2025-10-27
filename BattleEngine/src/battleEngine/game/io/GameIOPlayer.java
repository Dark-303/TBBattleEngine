package battleEngine.game.io;

import battleEngine.data.entities.EnemyData;
import battleEngine.data.entities.PlayerData;
import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;

public class GameIOPlayer implements GameIO {
    private PlayerData playerData;
    private EnemyData enemyData;
    private Attack playerPrimary;
    private Attack playerSecondary;
    private Attack playerUltimate;
    private Armor playerArmor;
    private Attack enemyPrimary;
    private Attack enemySecondary;
    private Attack enemyUltimate;
    private Armor enemyArmor;
    private double scaleFactor = 1;

    // Constructor
    public GameIOPlayer() {
        playerPrimary = new Attack("Ten Thousand Shadow Fists", 60.00, 40.00, 0.70, 0.60, 1, 1);
        playerSecondary = new Attack("Dragon Palm", 50.00, 40.00, 0.40, 0.30, 1, 1);
        playerUltimate = new Attack("Rose Thorns", 100.00, 90.00, 0.80, 0.5, 3, 1);
        playerArmor = new Armor("Tekketsuhana Armor", 100);
        playerData = new PlayerData(100, 7, armor, primary, secondary, ultimate, 1);

        enemyPrimary = new Attack("Blade of Flame", 60.00, 40.00, 0.70 * strengthMultiplier, 0.60 * strengthMultiplier, 1);
        enemySecondary = new Attack("Candle Slash", 50.00 * strengthMultiplier, 40.00 * strengthMultiplier,
                0.40 * strengthMultiplier, 0.30 * strengthMultiplier, 1);
        ultimate = new Attack("Tsunami of Fire", 100.00 * strengthMultiplier, 90.00 * strengthMultiplier,
                0.80 * strengthMultiplier, 0.5 * strengthMultiplier, 3);
        armor = new Armor("Fire Proof T Shirt", 100 * strengthMultiplier);
        enemyData = new EnemyData(100 * strengthMultiplier, 7 * strengthMultiplier, armor, primary, secondary,
                ultimate,
                1);
    }

    @Override
    public PlayerData updatePlayer() {
        return playerData;
    }

    @Override
    public ScaledFireEnemy updateEnemy() {
        return enemy;
    }
}