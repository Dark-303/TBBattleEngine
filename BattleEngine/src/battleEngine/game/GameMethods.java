package battleEngine.game;

import java.util.InputMismatchException;
import java.util.Scanner;
import battleEngine.BattleEngineUtil;
import battleEngine.data.entities.EnemyData;
import battleEngine.data.entities.PlayerData;
import battleEngine.data.models.Attack;
import battleEngine.game.io.GameIO.GameIOCooldowns;

public class GameMethods {
    private PlayerData playerData;
    private EnemyData enemyData;
    private GameIOCooldowns cooldowns;

    private Scanner selection = new Scanner(System.in);
    private int playerDamage;
    private int enemyDamage;
    private int playerEvadeAmount;
    private int enemyEvadeAmount;
    private int choice;

    public GameMethods(PlayerData playerData, EnemyData enemyData, GameIOCooldowns cooldowns) {
        this.playerData = playerData;
        this.enemyData = enemyData;
        this.cooldowns = cooldowns;
    }

    public void stats() {
        System.out.println("Player HP: " + playerData.getHealth());
        System.out.println("Armor HP: " + playerData.getArmor().getArmorHP());
        System.out.println(enemyData.getName() + " HP: " + enemyData.getHealth());
        System.out.println(enemyData.getName() + " Armor HP: " + enemyData.getArmor().getArmorHP());
        System.out.println();
    }

    public int runTurn(double currCooldown, Attack attack) {
        if (currCooldown >= attack.getCooldown()) {
            boolean crit = Math.random() < attack.getCritChance();
            playerDamage = (int) attack.useAttack(crit);
            cooldowns.setPlayerPrimary1Cooldown(cooldowns.getPlayerPrimary1Cooldown() + 1);
            cooldowns.setPlayerSecondary1Cooldown(cooldowns.getPlayerSecondary1Cooldown() + 1);
            cooldowns.setPlayerEvadeCooldown(cooldowns.getPlayerEvadeCooldown() + 1);
            cooldowns.setPlayerUltimateCooldown(cooldowns.getPlayerUltimateCooldown() + 1);
            cooldowns.setPlayerHyperModeCooldown(cooldowns.getPlayerHyperModeCooldown() + 1);
            System.out.println("Using " + attack.getName() + "!");
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
        boolean crit = Math.random() < attack.getCritChance();
        enemyDamage = (int) attack.useAttack(crit);
        cooldowns.setEnemyPrimary1Cooldown(cooldowns.getEnemyPrimary1Cooldown() + 1);
        cooldowns.setEnemySecondary1Cooldown(cooldowns.getEnemySecondary1Cooldown() + 1);
        cooldowns.setEnemyEvadeCooldown(cooldowns.getEnemyEvadeCooldown() + 1);
        cooldowns.setEnemyUltimateCooldown(0);
        cooldowns.setEnemyHyperModeCooldown(cooldowns.getEnemyHyperModeCooldown() + 1);
        System.out.println("Using " + attack.getName() + "!");
        if (crit)
            System.out.println("Critical hit!");
        System.out.println("Dealing " + enemyDamage + " damage...");
        System.out.println();
        return 0;
    }

    public int runEvade(double currCooldown, double speed) {
        if (currCooldown >= playerData.getEvadeCooldown()) {
            playerEvadeAmount = (int) (Math.random() * enemyDamage * speed / 5);
            cooldowns.setPlayerPrimary1Cooldown(cooldowns.getPlayerPrimary1Cooldown() + 1);
            cooldowns.setPlayerSecondary1Cooldown(cooldowns.getPlayerSecondary1Cooldown() + 1);
            cooldowns.setPlayerUltimateCooldown(cooldowns.getPlayerUltimateCooldown() + 1);
            cooldowns.setPlayerHyperModeCooldown(cooldowns.getPlayerHyperModeCooldown() + 1);
            System.out.println("Evaded " + playerEvadeAmount + " damage");
            System.out.println();
        } else {
            System.out.println("Ability on cooldown. Turn skipped.");
        }
        return 0;
    }

    public void displayMove(Attack move, int optionNum, int currCooldown) {
        System.out.println(optionNum + ". " + move.getName() + " : Enter " + optionNum);
        if (BattleEngineUtil.cooldown(currCooldown, move.getCooldown())) {
            System.out.println("   (Ready)");
        } else {
            int cooldownTotal = move.getCooldown() - currCooldown;
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

    public int playerChoice() {
        boolean check = false;
        while (!check) {
            stats();
            System.out.println("Select from the following options:");

            displayMove(playerData.getPrimaryAttack(), 1, cooldowns.getPlayerPrimary1Cooldown());
            displayMove(playerData.getSecondaryAttack(), 2, cooldowns.getPlayerSecondary1Cooldown());
            displayMove(3, "Evade", playerData.getEvadeCooldown(), cooldowns.getPlayerEvadeCooldown());
            displayMove(playerData.getUltimateAttack(), 4, cooldowns.getPlayerUltimateCooldown());
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
        return choice;
    }

    public PlayerData getPlayerData() { return playerData; }
    public void setPlayerData(PlayerData playerData) { this.playerData = playerData; }
    public EnemyData getEnemyData() { return enemyData; }
    public void setEnemyData(EnemyData enemyData) { this.enemyData = enemyData; }
    public GameIOCooldowns getCooldowns() { return cooldowns; }
    public void setCooldowns(GameIOCooldowns cooldowns) { this.cooldowns = cooldowns; }
    public int getPlayerDamage() { return playerDamage; }
    public void setPlayerDamage(int playerDamage) { this.playerDamage = playerDamage; }
    public int getEnemyDamage() { return enemyDamage; }
    public void setEnemyDamage(int enemyDamage) { this.enemyDamage = enemyDamage; }
    public int getPlayerEvadeAmount() { return playerEvadeAmount; }
    public void setPlayerEvadeAmount(int playerEvadeAmount) { this.playerEvadeAmount = playerEvadeAmount; }
    public int getEnemyEvadeAmount() { return enemyEvadeAmount; }
    public void setEnemyEvadeAmount(int enemyEvadeAmount) { this.enemyEvadeAmount = enemyEvadeAmount; }
    public int getChoice() { return choice; }
    public void setChoice(int choice) { this.choice = choice; }
}

