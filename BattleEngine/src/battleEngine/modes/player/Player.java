package battleEngine.modes.player;

import java.util.InputMismatchException;
import java.util.Scanner;

import battleEngine.BattleEngineUtil;
import battleEngine.modes.PlayerData;

public class Player {
    public boolean game = true;
    public boolean check = false;
    public Scanner selection = new Scanner(System.in);
    public int choice;
    public double playerDamage;
    public double enemyDamage;
    public double playerEvadeAmount;
    public double enemyEvadeAmount;
    public int playerPrimary1Cooldown = 1;
    public int playerSecondary1Cooldown = 1;
    public int playerEvadeCooldown = 1;
    public int playerUltimateCooldown = 0;
    public int playerHyperModeCooldown = 0;
    public int enemyPrimary1Cooldown = 1;
    public int enemySecondary1Cooldown = 1;
    public int enemyEvadeCooldown = 1;
    public int enemyUltimateCooldown = 0;
    public int enemyHyperModeCooldown = 0;

    public Player(PlayerData playerData, PlayerData enemyData) {
        System.out.println("Player mode initiated.");
        System.out.println();
        while (game) {
            while (!check) {
                System.out.println("Player's turn.");
                System.out.println("Player HP: " + playerData.health);
                System.out.println("Armor HP: " + playerData.armorHP);
                System.out.println("Enemy HP: " + enemyData.health);
                System.out.println("Enemy Armor HP: " + enemyData.armorHP);
                System.out.println();
                System.out.println("Select from the following options:");
                System.out.println("1. " + playerData.primaryAttack1Name + " : Enter 1");
                if (BattleEngineUtil.cooldown(playerPrimary1Cooldown, playerData.primaryAttack1Cooldown)) {
                    System.out.println("   (Ready)");
                } else {
                    if (playerData.primaryAttack1Cooldown - playerPrimary1Cooldown > 1) {
                        System.out.println("   (Cooldown: "
                                + (playerData.primaryAttack1Cooldown - playerPrimary1Cooldown) + " turns)");
                    } else {
                        System.out.println("   (Cooldown: "
                                + (playerData.primaryAttack1Cooldown - playerPrimary1Cooldown) + " turn)");
                    }
                }
                System.out.println("2. " + playerData.secondaryAttack1Name + " : Enter 2");
                if (BattleEngineUtil.cooldown(playerSecondary1Cooldown, playerData.secondaryAttack1Cooldown)) {
                    System.out.println("   (Ready)");
                } else {
                    if (playerData.secondaryAttack1Cooldown - playerSecondary1Cooldown > 1) {
                        System.out.println("   (Cooldown: "
                                + (playerData.secondaryAttack1Cooldown - playerSecondary1Cooldown) + " turns)");
                    } else {
                        System.out.println("   (Cooldown: "
                                + (playerData.secondaryAttack1Cooldown - playerSecondary1Cooldown) + " turn)");
                    }
                }
                System.out.println("3. Evade : Enter 3");
                if (BattleEngineUtil.cooldown(playerEvadeCooldown, playerData.evadeCooldown)) {
                    System.out.println("   (Ready)");
                } else {
                    if (playerData.evadeCooldown - playerEvadeCooldown > 1) {
                        System.out.println(
                                "   (Cooldown: " + (playerData.evadeCooldown - playerEvadeCooldown) + " turns)");
                    } else {
                        System.out.println(
                                "   (Cooldown: " + (playerData.evadeCooldown - playerEvadeCooldown) + " turn)");
                    }
                }
                System.out.println("4. " + playerData.ultimateName + " : Enter 4");
                if (BattleEngineUtil.cooldown(playerUltimateCooldown, playerData.ultimateCooldown)) {
                    System.out.println("   (Ready)");
                } else {
                    if (playerData.ultimateCooldown - playerUltimateCooldown > 1) {
                        System.out.println(
                                "   (Cooldown: " + (playerData.ultimateCooldown - playerUltimateCooldown) + " turns)");
                    } else {
                        System.out.println(
                                "   (Cooldown: " + (playerData.ultimateCooldown - playerUltimateCooldown) + " turn)");
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
                    if (playerPrimary1Cooldown >= playerData.primaryAttack1Cooldown) {
                        playerDamage = (float) Math.random()
                                * (playerData.primaryAttack1MaxDamage - playerData.primaryAttack1MinDamage)
                                + playerData.primaryAttack1MinDamage;
                        if (Math.random() < playerData.primaryAttack1CritChance) {
                            playerDamage *= 1 + playerData.primaryAttack1CritMultiplier;
                            System.out.println("Critical hit!");
                        }
                        playerDamage = BattleEngineUtil.round(playerDamage, 2);
                        playerPrimary1Cooldown = 0;
                        playerSecondary1Cooldown += 1;
                        playerEvadeCooldown += 1;
                        playerUltimateCooldown += 1;
                        playerHyperModeCooldown += 1;
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
                }
            }

            System.out.println("Enemy's turn.");
            System.out.println("Player HP: " + playerData.health);
            System.out.println("Armor HP: " + playerData.armorHP);
            System.out.println("Enemy HP: " + enemyData.health);
            System.out.println("Enemy Armor HP: " + enemyData.armorHP);
            System.out.println();
            if (playerDamage > (enemyData.health + enemyData.armorHP) / 2
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
}