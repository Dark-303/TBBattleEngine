package battleEngine.game;

import java.util.InputMismatchException;
import java.util.Scanner;

import battleEngine.BattleEngineUtil;
import battleEngine.Constants;
import battleEngine.Constants.Mode;
import battleEngine.data.entities.EnemyData;
import battleEngine.data.entities.PlayerData;
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

    public GameIO io;
    
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
    public int playerDamage;
    public int enemyDamage;

    // Evade Trackers
    public int playerEvadeAmount;
    public int enemyEvadeAmount;

    public Game(GameIO io) {
        this.io = io;
        
        // Initialize Entities
        playerData = io.updatePlayer();
        enemyData = io.updateEnemy();

        System.out.println("Player mode initiated.");
        System.out.println();
    }

    public void runGame() {
        io.runGame();
    }
}