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
            choice = playerChoice();
            switch (choice) {
                case 1:
                    cooldowns.playerPrimary1Cooldown = runTurn(cooldowns.playerPrimary1Cooldown,
                            playerData.primaryAttack);
                    break;
                case 2:
                    cooldowns.playerSecondary1Cooldown = runTurn(cooldowns.playerSecondary1Cooldown,
                            playerData.secondaryAttack);
                    break;
                case 3:
                    cooldowns.playerEvadeCooldown = runEvade(cooldowns.playerEvadeCooldown,
                            enemyData.speed);
                    break;
                case 4:
                    cooldowns.playerUltimateCooldown = runTurn(cooldowns.playerUltimateCooldown,
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

            game = checkDamage(playerDamage, enemyData);
            if (!game) {
                break;
            }

            // Convert to single method later
            stats();
            if (playerDamage > (enemyData.health + enemyData.armor.armorHP) * 0.8
                    && cooldowns.enemyEvadeCooldown >= enemyData.evadeCooldown) {
                enemyEvadeAmount = Math.random() * playerDamage * enemyData.speed / 5;
                playerDamage -= enemyEvadeAmount;
                enemyEvadeAmount = BattleEngineUtil.round(enemyEvadeAmount, 2);
                cooldowns.enemyPrimary1Cooldown += 1;
                cooldowns.enemySecondary1Cooldown += 1;
                cooldowns.enemyEvadeCooldown = 0;
                cooldowns.enemyUltimateCooldown += 1;
                cooldowns.enemyHyperModeCooldown += 1;
                enemyDamage = 0;
                System.out.println("Evaded " + enemyEvadeAmount + " damage");
                System.out.println();
            } else if (cooldowns.enemyUltimateCooldown >= enemyData.ultimateAttack.cooldown) {
                cooldowns.enemyUltimateCooldown = runEnemyTurn(cooldowns.enemyUltimateCooldown,
                        enemyData.ultimateAttack);
            } else if (cooldowns.enemyPrimary1Cooldown >= enemyData.primaryAttack.cooldown) {
                cooldowns.enemyPrimary1Cooldown = runEnemyTurn(cooldowns.enemyUltimateCooldown,
                        enemyData.ultimateAttack);
            } else if (cooldowns.enemySecondary1Cooldown >= enemyData.secondaryAttack.cooldown) {
                cooldowns.enemyPrimary2Cooldown = runEnemyTurn(cooldowns.enemyPrimary2Cooldown, enemyData.secondaryAttack);
            } else {
                System.out.println("Enemy ability on cooldown. Turn skipped.");
            }

            // Pause to show enemy action
            BattleEngineUtil.wait(3);

            game = checkDamage(enemyDamage, playerData);
            if (!game) {
                break;
            }
        }
    }

    public void stats() {
        System.out.println("Enemy's turn.");
        System.out.println("Player HP: " + playerData.health);
        System.out.println("Armor HP: " + playerData.armor.armorHP);
        System.out.println("Enemy HP: " + enemyData.health);
        System.out.println("Enemy Armor HP: " + enemyData.armor.armorHP);
        System.out.println();
    }

    public int runTurn(double currCooldown, Attack attack) {
        if (currCooldown >= attack.cooldown) {
            playerDamage = attack.useAttack(Math.random() < attack.critChance);
            cooldowns.playerPrimary1Cooldown += 1;
            cooldowns.playerSecondary1Cooldown += 1;
            cooldowns.playerEvadeCooldown += 1;
            cooldowns.playerUltimateCooldown += 1;
            cooldowns.playerHyperModeCooldown += 1;
            System.out.println("Using " + attack.name + "!");
            System.out.println("Dealing " + playerDamage + " damage...");
            System.out.println();
        } else {
            System.out.println("Ability on cooldown. Turn skipped.");
        }
        return 0;
    }

    public int runEnemyTurn(double currCooldown, Attack attack) {
        enemyDamage = enemyData.ultimateAttack.useAttack(Math.random() < attack.critChance);
        enemyDamage = BattleEngineUtil.round(enemyDamage, 2);
        cooldowns.enemyPrimary1Cooldown += 1;
        cooldowns.enemySecondary1Cooldown += 1;
        cooldowns.enemyEvadeCooldown += 1;
        cooldowns.enemyUltimateCooldown = 0;
        cooldowns.enemyHyperModeCooldown += 1;
        System.out.println("Using " + attack.name + "!");
        System.out.println("Dealing " + enemyDamage + " damage...");
        System.out.println();
        return 0;
    }

    public int runEvade(double currCooldown, double speed) {
        if (currCooldown >= playerData.evadeCooldown) {
            playerEvadeAmount = Math.random() * playerDamage * speed / 5;
            playerDamage -= playerEvadeAmount;
            playerEvadeAmount = BattleEngineUtil.round(playerEvadeAmount, 2);
            cooldowns.playerPrimary1Cooldown += 1;
            cooldowns.playerSecondary1Cooldown += 1;
            cooldowns.playerEvadeCooldown = 0;
            cooldowns.playerUltimateCooldown += 1;
            cooldowns.playerHyperModeCooldown += 1;
            enemyDamage = 0;
            System.out.println("Evaded " + playerEvadeAmount + " damage");
            System.out.println();
        } else {
            System.out.println("Ability on cooldown. Turn skipped.");
        }
        return 0;
    }

    public void displayMove(Attack move, int optionNum, int currCooldown) {
        System.out.println(optionNum + ". " + move.name + " : Enter " + optionNum);
        if (BattleEngineUtil.cooldown(currCooldown, move.cooldown)) {
            System.out.println("   (Ready)");
        } else {
            int cooldownTotal = move.cooldown - currCooldown;
            if (cooldownTotal > 1) {
                System.out.println("   (Cooldown: " + cooldownTotal + " turns)");
            } else {
                System.out.println("   (Cooldown: " + cooldownTotal + " turn)");
            }
        }
    }

    public void displayMove(int optionNum, String name, int maxCooldown, int currCooldown) {
        System.out.println(optionNum + ". " + name + " : Enter " + optionNum);
        if (BattleEngineUtil.cooldown(currCooldown, maxCooldown)) {
            System.out.println("   (Ready)");
        } else {
            int cooldownTotal = maxCooldown - currCooldown;
            if (cooldownTotal > 1) {
                System.out.println("   (Cooldown: " + cooldownTotal + " turns)");
            } else {
                System.out.println("   (Cooldown: " + cooldownTotal + " turn)");
            }
        }
    }

    public boolean checkDamage(double damage, PlayerData victim) {
        if (damage > 0) {
            if (victim.armor.armorHP > 0) {
                victim.armor.armorHP -= enemyDamage;
                victim.armor.armorHP = BattleEngineUtil.round(victim.armor.armorHP, 2);
                if (victim.armor.armorHP < 0) {
                    victim.health += victim.armor.armorHP;
                    victim.armor.armorHP = 0;
                }
            } else {
                victim.health -= enemyDamage;
                victim.health = BattleEngineUtil.round(victim.health, 2);
                if (victim.health < 0) {
                    victim.health = 0;
                }
            }
            if (victim.health <= 0) {
                System.out.println("You have been defeated!");
                return false;
            }
        }
        return true;
    }

    public boolean checkDamage(double damage, EnemyData victim) {
        if (damage > 0) {
            if (victim.armor.armorHP > 0) {
                victim.armor.armorHP -= enemyDamage;
                victim.armor.armorHP = BattleEngineUtil.round(victim.armor.armorHP, 2);
                if (victim.armor.armorHP < 0) {
                    victim.health += victim.armor.armorHP;
                    victim.armor.armorHP = 0;
                }
            } else {
                victim.health -= enemyDamage;
                victim.health = BattleEngineUtil.round(victim.health, 2);
                if (victim.health < 0) {
                    victim.health = 0;
                }
            }
            if (victim.health <= 0) {
                System.out.println("You have been defeated!");
                return false;
            }
        }
        return true;
    }

    public int playerChoice() {
        boolean check = false;
        while (!check) {
            stats();
            System.out.println("Select from the following options:");

            displayMove(playerData.primaryAttack, 1, cooldowns.playerPrimary1Cooldown);
            displayMove(playerData.secondaryAttack, 2, cooldowns.playerSecondary1Cooldown);
            displayMove(3, "Evade", playerData.evadeCooldown, cooldowns.playerEvadeCooldown);
            displayMove(playerData.ultimateAttack, 1, cooldowns.playerUltimateCooldown);
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
        return choice;
    }
}