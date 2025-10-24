package battleEngine.game;

import java.util.InputMismatchException;
import java.util.Scanner;

import battleEngine.BattleEngineUtil;
import battleEngine.Constants;
import battleEngine.Constants.Mode;
import battleEngine.data.entities.EnemyData;
import battleEngine.data.entities.PlayerData;
import battleEngine.data.entities.enemies.ScaledFireEnemy;
import battleEngine.data.models.Attack;
import battleEngine.game.io.GameIO;
import battleEngine.game.io.GameIOPlayer;
import battleEngine.game.io.GameIO.GameIOCooldowns;

public class Game {
    private static Game instance;

    public static Game getInstance() {
        if (instance == null) {
            switch (Constants.currentMode) {
                case Mode.PLAYER:
                    instance = new Game(new GameIOPlayer());
                    break;
                case Mode.WEAPON:
                    // instance = new Game(new GameIOWeapon());
                    break;
                default:
                    instance = new Game(new GameIO() {
                    });
                    break;
            }
        }
        return instance;
    }

    // Game Variables
    public boolean game = true;
    public boolean check = false;
    public Scanner selection = new Scanner(System.in);
    public int choice;
    public GameIOCooldowns cooldowns = new GameIOCooldowns();

    // Entities
    public PlayerData playerData;
    public EnemyData enemyData;

    // Damage Trackers
    public double playerDamage;
    public double enemyDamage;

    // Evade Trackers
    public double playerEvadeAmount;
    public double enemyEvadeAmount;

    public Game(GameIO io) {
        // Initialize Entities
        playerData = io.updatePlayer();
        enemyData = io.updateEnemy().getEnemyData(); // Gets the scaled EnemyData

        System.out.println("Player mode initiated.");
        System.out.println();
        while (game) {
            while (!check) {
                System.out.println("Player's turn.");
                System.out.println("Player HP: " + playerData.health);
                System.out.println("Armor HP: " + playerData.armor.armorHP);
                System.out.println("Enemy HP: " + enemyData.health);
                System.out.println("Enemy Armor HP: " + enemyData.armor.armorHP);
                System.out.println();
                System.out.println("Select from the following options:");
                System.out.println("1. " + playerData.primaryAttack.name + " : Enter 1");
                if (BattleEngineUtil.cooldown(cooldowns.playerPrimary1Cooldown, playerData.primaryAttack.cooldown)) {
                    System.out.println("   (Ready)");
                } else {
                    if (playerData.primaryAttack.cooldown - cooldowns.playerPrimary1Cooldown > 1) {
                        System.out.println("   (Cooldown: "
                                + (playerData.primaryAttack.cooldown - cooldowns.playerPrimary1Cooldown) + " turns)");
                    } else {
                        System.out.println("   (Cooldown: "
                                + (playerData.primaryAttack.cooldown - cooldowns.playerPrimary1Cooldown) + " turn)");
                    }
                }
                System.out.println("2. " + playerData.secondaryAttack.name + " : Enter 2");
                if (BattleEngineUtil.cooldown(cooldowns.playerSecondary1Cooldown,
                        playerData.secondaryAttack.cooldown)) {
                    System.out.println("   (Ready)");
                } else {
                    if (playerData.secondaryAttack.cooldown - cooldowns.playerSecondary1Cooldown > 1) {
                        System.out.println("   (Cooldown: "
                                + (playerData.secondaryAttack.cooldown - cooldowns.playerSecondary1Cooldown)
                                + " turns)");
                    } else {
                        System.out.println("   (Cooldown: "
                                + (playerData.secondaryAttack.cooldown - cooldowns.playerSecondary1Cooldown)
                                + " turn)");
                    }
                }
                System.out.println("3. Evade : Enter 3");
                if (BattleEngineUtil.cooldown(cooldowns.playerEvadeCooldown, playerData.evadeCooldown)) {
                    System.out.println("   (Ready)");
                } else {
                    if (playerData.evadeCooldown - cooldowns.playerEvadeCooldown > 1) {
                        System.out.println(
                                "   (Cooldown: " + (playerData.evadeCooldown - cooldowns.playerEvadeCooldown)
                                        + " turns)");
                    } else {
                        System.out.println(
                                "   (Cooldown: " + (playerData.evadeCooldown - cooldowns.playerEvadeCooldown)
                                        + " turn)");
                    }
                }
                System.out.println("4. " + playerData.ultimateAttack.cooldown + " : Enter 4");
                if (BattleEngineUtil.cooldown(cooldowns.playerUltimateCooldown, playerData.ultimateAttack.cooldown)) {
                    System.out.println("   (Ready)");
                } else {
                    if (playerData.ultimateAttack.cooldown - cooldowns.playerUltimateCooldown > 1) {
                        System.out.println(
                                "   (Cooldown: "
                                        + (playerData.ultimateAttack.cooldown - cooldowns.playerUltimateCooldown)
                                        + " turns)");
                    } else {
                        System.out.println(
                                "   (Cooldown: "
                                        + (playerData.ultimateAttack.cooldown - cooldowns.playerUltimateCooldown)
                                        + " turn)");
                    }
                }
                System.out.println("5. Enter Hyper Mode : Enter 5");
                /*
                 * if (BattleEngineUtil.cooldown(playerHyperModeCooldown,
                 * playerData.hyperModeCooldown))) {
                 * System.out.println("   (Ready)");
                 * } else {
                 * System.out.println("   (Cooldown: " + (playerData.hyperModeCooldown -
                 * playerHyperModeCooldown) + " turns)");
                 * }
                 */
                System.out.print("Enter your choice: ");
                try {
                    choice = selection.nextInt();
                    check = true;
                    System.out.println();
                } catch (InputMismatchException e) {
                    System.out.println("error: not a number");
                    System.out.println();
                    selection.next();
                }
            }
            check = false;
            switch (choice) {
                case 1:
                    if (cooldowns.playerPrimary1Cooldown >= playerData.primaryAttack1Cooldown) {
                        playerDamage = playerData.primaryAttack
                                .useAttack(Math.random() < playerData.primaryAttack.critChance);
                        cooldowns.playerPrimary1Cooldown = 0;
                        cooldowns.playerSecondary1Cooldown += 1;
                        cooldowns.playerEvadeCooldown += 1;
                        cooldowns.playerUltimateCooldown += 1;
                        cooldowns.playerHyperModeCooldown += 1;
                        System.out.println("Using " + playerData.primaryAttack1Name + "!");
                        System.out.println("Dealing " + playerDamage + " damage...");
                        System.out.println();
                    } else {
                        System.out.println("Ability on cooldown. Turn skipped.");
                    }
                    break;
                case 2:
                    if (playerSecondary1Cooldown >= playerData.secondaryAttack1Cooldown) {
                        playerDamage = (float) Math.random()
                                * (playerData.secondaryAttack1MaxDamage - playerData.secondaryAttack1MinDamage)
                                + playerData.secondaryAttack1MinDamage;
                        if (Math.random() < playerData.secondaryAttack1CritChance) {
                            playerDamage *= 1 + playerData.secondaryAttack1CritMultiplier;
                            System.out.println("Critical hit!");
                        }
                        playerDamage = BattleEngineUtil.round(playerDamage, 2);
                        playerPrimary1Cooldown += 1;
                        playerSecondary1Cooldown = 0;
                        playerEvadeCooldown += 1;
                        playerUltimateCooldown += 1;
                        playerHyperModeCooldown += 1;
                        System.out.println("Using " + playerData.secondaryAttack1Name + "!");
                        System.out.println("Dealing " + playerDamage + " damage...");
                        System.out.println();
                    } else {
                        System.out.println("Ability on cooldown. Turn skipped.");
                    }
                    break;
                case 3:
                    if (playerEvadeCooldown >= playerData.evadeCooldown)
                        playerEvadeAmount = Math.random() * enemyDamage * playerData.speed / 5;
                    playerEvadeAmount = BattleEngineUtil.round(playerEvadeAmount, 2);
                    enemyDamage -= playerEvadeAmount;
                    playerPrimary1Cooldown += 1;
                    playerSecondary1Cooldown += 1;
                    playerEvadeCooldown = 0;
                    playerUltimateCooldown += 1;
                    playerHyperModeCooldown += 1;
                    playerDamage = 0;
                    System.out.println("Evaded " + playerEvadeAmount + " damage");
                    System.out.println();
                    break;
                case 4:
                    if (playerUltimateCooldown >= playerData.ultimateCooldown) {
                        playerDamage = (float) Math.random()
                                * (playerData.ultimateMaxDamage - playerData.ultimateMinDamage)
                                + playerData.ultimateMinDamage;
                        if (Math.random() < playerData.ultimateCritChance) {
                            playerDamage *= 1 + playerData.ultimateCritMultiplier;
                            System.out.println("Critical hit!");
                        }
                        playerDamage = BattleEngineUtil.round(playerDamage, 2);
                        playerPrimary1Cooldown += 1;
                        playerSecondary1Cooldown += 1;
                        playerEvadeCooldown += 1;
                        playerUltimateCooldown = 0;
                        playerHyperModeCooldown += 1;
                        System.out.println("Using " + playerData.ultimateName + "!");
                        System.out.println("Dealing " + playerDamage + " damage...");
                        System.out.println();
                    } else {
                        System.out.println("Ability on cooldown. Turn skipped.");
                    }
                    System.out.println();
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

            if (enemyDamage > 0) {
                if (playerData.armorHP > 0) {
                    playerData.armorHP -= enemyDamage;
                    playerData.armorHP = BattleEngineUtil.round(playerData.armorHP, 2);
                    if (playerData.armorHP < 0) {
                        playerData.health += playerData.armorHP;
                        playerData.armorHP = 0;
                    }
                } else {
                    playerData.health -= enemyDamage;
                    playerData.health = BattleEngineUtil.round(playerData.health, 2);
                    if (playerData.health < 0) {
                        playerData.health = 0;
                    }
                }
                if (playerData.health <= 0) {
                    System.out.println("You have been defeated!");
                    game = false;
                    break;
                }
            }

            System.out.println("Enemy's turn.");
            System.out.println("Player HP: " + playerData.health);
            System.out.println("Armor HP: " + playerData.armorHP);
            System.out.println("Enemy HP: " + enemyData.health);
            System.out.println("Enemy Armor HP: " + enemyData.armorHP);
            System.out.println();
            if (playerDamage > (enemyData.health + enemyData.armorHP) * 0.8
                    && enemyEvadeCooldown >= enemyData.evadeCooldown) {
                enemyEvadeAmount = Math.random() * playerDamage * enemyData.speed / 5;
                playerDamage -= enemyEvadeAmount;
                enemyEvadeAmount = BattleEngineUtil.round(enemyEvadeAmount, 2);
                enemyPrimary1Cooldown += 1;
                enemySecondary1Cooldown += 1;
                enemyEvadeCooldown = 0;
                enemyUltimateCooldown += 1;
                enemyHyperModeCooldown += 1;
                enemyDamage = 0;
                System.out.println("Evaded " + enemyEvadeAmount + " damage");
                System.out.println();
            } else if (enemyUltimateCooldown >= enemyData.ultimateCooldown) {
                enemyDamage = (float) Math.random()
                        * (enemyData.ultimateMaxDamage - enemyData.ultimateMinDamage)
                        + enemyData.ultimateMinDamage;
                if (Math.random() < enemyData.ultimateCritChance) {
                    enemyDamage *= 1 + enemyData.ultimateCritMultiplier;
                    System.out.println("Critical hit!");
                }
                enemyDamage = BattleEngineUtil.round(enemyDamage, 2);
                enemyPrimary1Cooldown += 1;
                enemySecondary1Cooldown += 1;
                enemyEvadeCooldown += 1;
                enemyUltimateCooldown = 0;
                enemyHyperModeCooldown += 1;
                System.out.println("Using " + enemyData.ultimateName + "!");
                System.out.println("Dealing " + enemyDamage + " damage...");
                System.out.println();
            } else if (enemyPrimary1Cooldown >= enemyData.primaryAttack1Cooldown) {
                enemyDamage = (float) Math.random()
                        * (enemyData.primaryAttack1MaxDamage - enemyData.primaryAttack1MinDamage)
                        + enemyData.primaryAttack1MinDamage;
                if (Math.random() < enemyData.primaryAttack1CritChance) {
                    enemyDamage *= 1 + enemyData.primaryAttack1CritMultiplier;
                    System.out.println("Critical hit!");
                }
                enemyDamage = BattleEngineUtil.round(enemyDamage, 2);
                enemyPrimary1Cooldown = 0;
                enemySecondary1Cooldown += 1;
                enemyEvadeCooldown += 1;
                enemyUltimateCooldown += 1;
                enemyHyperModeCooldown += 1;
                System.out.println("Using " + enemyData.primaryAttack1Name + "!");
                System.out.println("Dealing " + enemyDamage + " damage...");
                System.out.println();
            } else if (enemySecondary1Cooldown >= enemyData.secondaryAttack1Cooldown) {
                enemyDamage = (float) Math.random()
                        * (enemyData.secondaryAttack1MaxDamage - enemyData.secondaryAttack1MinDamage)
                        + enemyData.secondaryAttack1MinDamage;
                if (Math.random() < enemyData.secondaryAttack1CritChance) {
                    enemyDamage *= 1 + enemyData.secondaryAttack1CritMultiplier;
                    System.out.println("Critical hit!");
                }
                enemyDamage = BattleEngineUtil.round(enemyDamage, 2);
                enemyPrimary1Cooldown += 1;
                enemySecondary1Cooldown = 0;
                enemyEvadeCooldown += 1;
                enemyUltimateCooldown += 1;
                enemyHyperModeCooldown += 1;
                System.out.println("Using " + enemyData.secondaryAttack1Name + "!");
                System.out.println("Dealing " + enemyDamage + " damage...");
                System.out.println();
            } else {
                System.out.println("Enemy ability on cooldown. Turn skipped.");
            }

            // Pause to show enemy action
            BattleEngineUtil.wait(3);

            if (playerDamage > 0) {
                if (enemyData.armorHP > 0) {
                    enemyData.armorHP -= playerDamage;
                    enemyData.armorHP = BattleEngineUtil.round(enemyData.armorHP, 2);
                    if (enemyData.armorHP < 0) {
                        enemyData.health += enemyData.armorHP;
                        enemyData.armorHP = 0;
                    }
                } else {
                    enemyData.health -= playerDamage;
                    enemyData.health = BattleEngineUtil.round(enemyData.health, 2);
                    if (enemyData.health < 0) {
                        enemyData.health = 0;
                    }
                }
                if (enemyData.health <= 0) {
                    System.out.println("You have defeated the enemy!");
                    game = false;
                }
            }
        }
    }

    public void runTurnPlayer(double currCooldown, Attack attack) {
        if (currCooldown >= attack.cooldown) {
            playerDamage = attack.useAttack(Math.random() < attack.critChance);
            cooldowns.playerPrimary1Cooldown += 1;
            cooldowns.playerSecondary1Cooldown += 1;
            cooldowns.playerEvadeCooldown += 1;
            cooldowns.playerUltimateCooldown += 1;
            cooldowns.playerHyperModeCooldown += 1;
            currCooldown = 0;
            System.out.println("Using " + attack.name + "!");
            System.out.println("Dealing " + playerDamage + " damage...");
            System.out.println();
        } else {
            System.out.println("Ability on cooldown. Turn skipped.");
        }
    }
}