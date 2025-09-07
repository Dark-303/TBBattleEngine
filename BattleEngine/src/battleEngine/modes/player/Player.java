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
        while(game) {
            while (!check) {
                System.out.println("Player's turn.");
                System.out.println("Player HP: " + playerData.health);
                System.out.println("Armor HP: " + playerData.armorHP);
                System.out.println("Enemy HP: " + enemyData.health);
                System.out.println("Enemy Armor HP: " + enemyData.armorHP);
                System.out.println();                
                System.out.println("Select from the following options:");
                System.out.println("1. " + playerData.primaryAttack1Name + " : Enter 1");
                System.out.println("2. " + playerData.secondaryAttack1Name + " : Enter 2");
                System.out.println("3. Evade : Enter 3");
                System.out.println("4. " + playerData.ultimateName + " : Enter 4");
                System.out.println("5. Enter Hyper Mode : Enter 5");
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
                    if (playerPrimary1Cooldown > 0) {
                        playerDamage = (float)Math.random() * (playerData.primaryAttack1MaxDamage - playerData.primaryAttack1MinDamage) + playerData.primaryAttack1MinDamage;
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
                        System.out.println("Dealing " + playerDamage + " damage...");
                        System.out.println();
                    } else {
                        System.out.println("Ability on cooldown. Turn skipped.");
                    }
                    break;
                case 2:
                    if (playerSecondary1Cooldown > 0) {
                        playerDamage = (float)Math.random() * (playerData.secondaryAttack1MaxDamage - playerData.secondaryAttack1MinDamage) + playerData.secondaryAttack1MinDamage;
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
                        System.out.println("Dealing " + playerDamage + " damage...");
                        System.out.println();
                    } else {
                        System.out.println("Ability on cooldown. Turn skipped.");
                    }
                    break;
                case 3:
                    playerEvadeAmount = Math.random()*enemyDamage*playerData.speed/5;
                    enemyDamage -= playerEvadeAmount;
                    playerPrimary1Cooldown += 1;
                    playerSecondary1Cooldown += 1;
                    playerEvadeCooldown = 0;    
                    playerUltimateCooldown += 1;
                    playerHyperModeCooldown += 1;
                    System.out.println("Evaded " + playerEvadeAmount + " damage");
                    System.out.println();
                    break;
                case 4:
                    System.out.println("You used " + playerData.ultimateName + "!");
                    //System.out.println("It dealt " + playerData.ultimateDamage + " damage!");
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
            if (playerData.armorHP > 0) {
                playerData.armorHP -= enemyDamage;
            } else {
                playerData.health -= enemyDamage;
            }
            if (playerData.health <= 0) {
                System.out.println("You have been defeated!");
                game = false;
            }

            System.out.println("Enemy's turn.");
            System.out.println("Player HP: " + playerData.health);
            System.out.println("Armor HP: " + playerData.armorHP);
            System.out.println("Enemy HP: " + enemyData.health);
            System.out.println("Enemy Armor HP: " + enemyData.armorHP);
            if (playerDamage > (enemyData.health+enemyData.armorHP)/2 && enemyEvadeCooldown > 1) {
                enemyEvadeAmount = Math.random()*playerDamage*enemyData.speed/5;
                    playerDamage -= enemyEvadeAmount;
                    enemyPrimary1Cooldown += 1;
                    enemySecondary1Cooldown += 1;
                    enemyEvadeCooldown = 0;    
                    enemyUltimateCooldown += 1;
                    enemyHyperModeCooldown += 1;
                    System.out.println("Evaded " + enemyEvadeAmount + " damage");
                    System.out.println();
            }
        }
    }
}
