package battleEngine.game.io;

import battleEngine.BattleEngineUtil;
import battleEngine.data.entities.EnemyData;
import battleEngine.data.entities.PlayerData;
import battleEngine.data.entities.enemies.TestBoss;
import battleEngine.data.entities.enemies.TestBoss2;
import battleEngine.data.entities.enemies.WeakFireEnemy;
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

        enemyData = new TestBoss2(scaleFactor);

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
                    cooldowns.playerPrimary1Cooldown = gm.runTurn(cooldowns.playerPrimary1Cooldown,
                            playerData.primaryAttack);
                    break;
                case 2:
                    cooldowns.playerSecondary1Cooldown = gm.runTurn(cooldowns.playerSecondary1Cooldown,
                            playerData.secondaryAttack);
                    break;
                case 3:
                    cooldowns.playerEvadeCooldown = gm.runEvade(cooldowns.playerEvadeCooldown,
                            enemyData.speed);
                    break;
                case 4:
                    cooldowns.playerUltimateCooldown = gm.runTurn(cooldowns.playerUltimateCooldown,
                            playerData.ultimateAttack);
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

            game = enemyData.checkDamage(gm.playerDamage);
            if (!game)
                break;

            // Convert to single method later
            System.out.println(enemyData.name + "'s Turn");
            gm.stats();
            if (gm.playerDamage > (enemyData.health + enemyData.armor.armorHP) * 0.8
                    && cooldowns.enemyEvadeCooldown >= enemyData.evadeCooldown) {
                gm.enemyEvadeAmount = (int) (Math.random() * gm.playerDamage * enemyData.speed / 5);
                gm.playerDamage -= gm.enemyEvadeAmount;
                cooldowns.enemyPrimary1Cooldown += 1;
                cooldowns.enemySecondary1Cooldown += 1;
                cooldowns.enemyEvadeCooldown = 0;
                cooldowns.enemyUltimateCooldown += 1;
                cooldowns.enemyHyperModeCooldown += 1;
                gm.enemyDamage = 0;
                System.out.println("Evaded " + gm.enemyEvadeAmount + " damage");
                System.out.println();
            } else if (cooldowns.enemyUltimateCooldown >= enemyData.ultimateAttack.cooldown) {
                cooldowns.enemyUltimateCooldown = gm.runEnemyTurn(cooldowns.enemyUltimateCooldown,
                        enemyData.ultimateAttack);
            } else if (cooldowns.enemyPrimary1Cooldown >= enemyData.primaryAttack.cooldown) {
                cooldowns.enemyPrimary1Cooldown = gm.runEnemyTurn(cooldowns.enemyPrimary1Cooldown,
                        enemyData.primaryAttack);
            } else if (cooldowns.enemySecondary1Cooldown >= enemyData.secondaryAttack.cooldown) {
                cooldowns.enemyPrimary2Cooldown = gm.runEnemyTurn(cooldowns.enemyPrimary2Cooldown,
                        enemyData.secondaryAttack);
            } else {
                System.out.println("Enemy ability on cooldown. Turn skipped.");
            }

            // Pause to show enemy action
            BattleEngineUtil.wait(3);

            game = playerData.checkDamage(gm.enemyDamage);
            if (!game)
                break;
        }
    }
}