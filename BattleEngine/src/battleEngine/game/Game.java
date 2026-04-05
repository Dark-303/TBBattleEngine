package battleEngine.game;

import battleEngine.Constants;
import battleEngine.game.io.GameIO;
import battleEngine.game.io.GameIOPlayer;

public class Game {
    private static Game instance;

    public static Game getInstance() {
        if (instance == null) {
            switch (Constants.currentMode) {
                case PLAYER:
                    instance = new Game(new GameIOPlayer());
                    System.out.println("Player mode initiated.");
                    System.out.println();
                    break;
                case WEAPON:
                    // instance = new Game(new GameIOWeapon());
                    break;
                default:
                    instance = new Game(new GameIO() {});
                    break;
            }
        }
        return instance;
    }

    public GameIO io;

    public Game(GameIO io) {
        this.io = io;
    }

    public void runGame() {
        io.runGame();
    }
}
