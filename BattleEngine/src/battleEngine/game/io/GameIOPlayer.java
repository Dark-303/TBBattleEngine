package battleEngine.game.io;

import battleEngine.BattleEngineUtil;
import battleEngine.data.entities.EnemyData;
import battleEngine.data.entities.PlayerData;
import battleEngine.data.entities.enemies.TestBoss;
import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;
import battleEngine.game.GameMethods;

public class GameIOPlayer implements GameIO {
    private PlayerData playerData;
    private EnemyData enemyData;
    private Attack playerPrimary;
    private Attack playerSecondary;
    private Attack playerUltimate;
    private Armor playerArmor;
    private GameIOCooldowns cooldowns;
    private double scaleFactor = 1;

    private GameMethods gm;

    private int choice;

    // Constructor
    public GameIOPlayer() {
        playerPrimary = new Attack("Ten Thousand Shadow Fists", 60, 40, 0.70, 0.60, 1, 1);
        playerSecondary = new Attack("Dragon Palm", 50, 40, 0.40, 0.30, 1, 1);
        playerUltimate = new Attack("Rose Thorns", 100, 90, 0.80, 0.5, 3, 1);
        playerArmor = new Armor("Tekketsuhana Armor", 100);
        playerData = new PlayerData(100, 7, playerArmor, playerPrimary, playerSecondary, playerUltimate, 1);

        enemyData = new TestBoss(scaleFactor);

        cooldowns = new GameIOCooldowns();

        // LOL GUNDAM REFERENCE!
        gm = new GameMethods(playerData, enemyData, cooldowns);
    }

    @Override
    public PlayerData updatePlayer() {
        return playerData;
    }

    @Override
    public EnemyData updateEnemy() {
        return enemyData;
    }

    @Override
    public void runGame() {
        boolean game = true;
        while (game) {
            System.out.println("Player's Turn");
            choice = gm.playerChoice();
            switch (choice) {
                case 1:
                    cooldowns.setPlayerPrimary1Cooldown(gm.runTurn(cooldowns.getPlayerPrimary1Cooldown(),
                            playerData.getPrimaryAttack()));
                    break;
                case 2:
                    cooldowns.setPlayerSecondary1Cooldown(gm.runTurn(cooldowns.getPlayerSecondary1Cooldown(),
                            playerData.getSecondaryAttack()));
                    break;
                case 3:
                    cooldowns.setPlayerEvadeCooldown(gm.runEvade(cooldowns.getPlayerEvadeCooldown(),
                            enemyData.getSpeed()));
                    break;

                case 4:
                    cooldowns.setPlayerUltimateCooldown(gm.runTurn(cooldowns.getPlayerUltimateCooldown(),
                            playerData.getUltimateAttack()));
                    break;
                case 5:
                    System.out.println("You entered Hyper Mode!");
                    System.out.println("Your attacks are now stronger, but you can only use basic attacks.");
                    System.out.println();
                    break;
                default:
                    System.out.println("error: invalid input");
                    System.out.println();
                    break;
            }

            // Pause to show your action
            BattleEngineUtil.wait(3);

            game = playerData.checkDamage(gm.getEnemyDamage(), gm.getPlayerEvadeAmount());
            gm.setPlayerEvadeAmount(0);
            gm.setEnemyDamage(0);
            if (!game) break;

            // Convert to single method later
            System.out.println(enemyData.getName() + "'s Turn");
            gm.stats();
            if (gm.getPlayerDamage() > (enemyData.getHealth() + enemyData.getArmor().getArmorHP()) * 0.8
                    && cooldowns.getEnemyEvadeCooldown() >= enemyData.getEvadeCooldown()) {
                gm.setEnemyEvadeAmount((int) (Math.random() * gm.getPlayerDamage() * enemyData.getSpeed() / 5));
                cooldowns.setEnemyPrimary1Cooldown(cooldowns.getEnemyPrimary1Cooldown() + 1);
                cooldowns.setEnemySecondary1Cooldown(cooldowns.getEnemySecondary1Cooldown() + 1);
                cooldowns.setEnemyEvadeCooldown(0);
                cooldowns.setEnemyUltimateCooldown(cooldowns.getEnemyUltimateCooldown() + 1);
                cooldowns.setEnemyHyperModeCooldown(cooldowns.getEnemyHyperModeCooldown() + 1);
                System.out.println("Evaded " + gm.getEnemyEvadeAmount() + " damage");
                System.out.println();
            } else if (cooldowns.getEnemyUltimateCooldown() >= enemyData.getUltimateAttack().getCooldown()) {
                cooldowns.setEnemyUltimateCooldown(gm.runEnemyTurn(cooldowns.getEnemyUltimateCooldown(),
                        enemyData.getUltimateAttack()));
            } else if (cooldowns.getEnemyPrimary1Cooldown() >= enemyData.getPrimaryAttack().getCooldown()) {
                cooldowns.setEnemyPrimary1Cooldown(gm.runEnemyTurn(cooldowns.getEnemyPrimary1Cooldown(),
                        enemyData.getPrimaryAttack()));
            } else if (cooldowns.getEnemySecondary1Cooldown() >= enemyData.getSecondaryAttack().getCooldown()) {
                cooldowns.setEnemyPrimary2Cooldown(gm.runEnemyTurn(cooldowns.getEnemyPrimary2Cooldown(),
                        enemyData.getSecondaryAttack()));
            } else {
                System.out.println("Enemy ability on cooldown. Turn skipped.");
            }

            // Pause to show enemy action
            BattleEngineUtil.wait(3);

            game = enemyData.checkDamage(gm.getPlayerDamage(), gm.getEnemyEvadeAmount());
            gm.setEnemyEvadeAmount(0);
            gm.setPlayerDamage(0);
            if (!game) break;
        }
    }
}