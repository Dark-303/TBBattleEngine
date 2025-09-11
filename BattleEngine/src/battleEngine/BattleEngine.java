package battleEngine;

import battleEngine.modes.ModeController;
import battleEngine.modes.PlayerData;

public class BattleEngine {
    public Modes currentMode;
    public ModeController modeController;
    public PlayerData playerData;
    public PlayerData enemyData;

    public BattleEngine() {
        currentMode = Modes.PLAYER;
        playerData = new PlayerData();
        setPlayerData();
        enemyData = new PlayerData();
        setEnemyData();
        modeController = new ModeController(currentMode, playerData, enemyData);
    }

    public void setPlayerData() {
        playerData.setArmor("Tekketsuhana Armor", 100);
        playerData.setGeneralStats(100, 20);
        playerData.setPrimaryAttack1("Fist", "Ten Thousand Shadow Fists", 60.00, 40.00, 0.70, 0.60, 1);
        playerData.setSecondaryAttack1("Dragon Palm", 50.00, 40.00, 0.40, 0.30, 1);
        playerData.setUltimateMove("Fist", "Rose Thorns", 100.00, 90.00, 0.80, 0.5, 3);
        playerData.setHyperMode(0.5, 3, 3);
    }

    public void setEnemyData() {
        enemyData.setArmor("Nanolaminate Armor", 80);
        enemyData.setGeneralStats(100, 10);
        enemyData.setPrimaryAttack1("Fist", "Punch", 50.00, 40.00, 0.70, 0.60, 1);
        enemyData.setSecondaryAttack1("Palm", 40.00, 30.00, 0.40, 0.30, 1);
        enemyData.setUltimateMove("Fist", "Pile Bunker", 80.00, 60.00, 0.80, 0.5, 3);
        enemyData.setHyperMode(0.5, 3, 3);
    }

}
