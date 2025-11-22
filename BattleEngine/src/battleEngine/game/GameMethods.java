package battleEngine.game;

import java.util.InputMismatchException;
import java.util.Scanner;

import battleEngine.BattleEngineUtil;
import battleEngine.data.entities.EnemyData;
import battleEngine.data.entities.PlayerData;
import battleEngine.data.models.Attack;
import battleEngine.game.io.GameIO.GameIOCooldowns;

public class GameMethods {
    public PlayerData playerData;
    public EnemyData enemyData;
    public GameIOCooldowns cooldowns;

    public Scanner selection = new Scanner(System.in);
    public int playerDamage;
    public int enemyDamage;
    public int playerEvadeAmount;
    public int enemyEvadeAmount;
    public int choice;

    public GameMethods(PlayerData playerData, EnemyData enemyData, GameIOCooldowns cooldowns) {
        this.playerData = playerData;
        this.enemyData = enemyData;
        this.cooldowns = cooldowns;
    }

    public void stats() {
        System.out.println("Player HP: " + playerData.health);
        System.out.println("Armor HP: " + playerData.armor.armorHP);
        System.out.println("Enemy HP: " + enemyData.health);
        System.out.println("Enemy Armor HP: " + enemyData.armor.armorHP);
        System.out.println();
    }

    public int runTurn(double currCooldown, Attack attack) {
        if (currCooldown >= attack.cooldown) {
            boolean crit = Math.random() < attack.critChance;
            playerDamage = (int) attack.useAttack(crit);
            cooldowns.playerPrimary1Cooldown += 1;
            cooldowns.playerSecondary1Cooldown += 1;
            cooldowns.playerEvadeCooldown += 1;
            cooldowns.playerUltimateCooldown += 1;
            cooldowns.playerHyperModeCooldown += 1;
            System.out.println("Using " + attack.name + "!");
            if (crit)
                System.out.println("Critical hit!");
            System.out.println("Dealing " + playerDamage + " damage...");
            System.out.println();
        } else {
            System.out.println("Ability on cooldown. Turn skipped.");
        }
        return 0;
    }

    public int runEnemyTurn(double currCooldown, Attack attack) {
        boolean crit = Math.random() < attack.critChance;
        enemyDamage = (int) attack.useAttack(crit);
        cooldowns.enemyPrimary1Cooldown += 1;
        cooldowns.enemySecondary1Cooldown += 1;
        cooldowns.enemyEvadeCooldown += 1;
        cooldowns.enemyUltimateCooldown = 0;
        cooldowns.enemyHyperModeCooldown += 1;
        System.out.println("Using " + attack.name + "!");
        if (crit)
            System.out.println("Critical hit!");
        System.out.println("Dealing " + enemyDamage + " damage...");
        System.out.println();
        return 0;
    }

    public int runEvade(double currCooldown, double speed) {
        if (currCooldown >= playerData.evadeCooldown) {
            playerEvadeAmount = (int) (Math.random() * playerDamage * speed / 5);
            playerDamage -= playerEvadeAmount;
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
                if (victim.armor.armorHP < 0) {
                    victim.health += victim.armor.armorHP;
                    victim.armor.armorHP = 0;
                }
            } else {
                victim.health -= enemyDamage;
                victim.health = (int) victim.health;
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
                victim.armor.armorHP -= playerDamage;
                victim.armor.armorHP = (int) victim.armor.armorHP;
                if (victim.armor.armorHP < 0) {
                    victim.health += victim.armor.armorHP;
                    victim.armor.armorHP = 0;
                }
            } else {
                victim.health -= playerDamage;
                victim.health = (int) victim.health;
                if (victim.health < 0) {
                    victim.health = 0;
                }
            }
            if (victim.health <= 0) {
                System.out.println("You have defeated your opponent!");
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
            displayMove(playerData.ultimateAttack, 4, cooldowns.playerUltimateCooldown);
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
