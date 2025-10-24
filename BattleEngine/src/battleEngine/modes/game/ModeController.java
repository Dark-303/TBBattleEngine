package battleEngine.modes.game;

import battleEngine.Modes;
import battleEngine.modes.PlayerData;

public class ModeController {
    public GameIOPlayer player;

    public ModeController(Modes currentMode, PlayerData playerData, PlayerData enemyData) {
        switch (currentMode) {
            case Modes.PLAYER:
                System.out.println("Initializing player mode...");
                player = new GameIOPlayer(playerData, enemyData);
                System.out.println("Match Completed");
                break;
            case Modes.WEAPON:
                System.out.println("Battle ended!");
                break;
            case Modes.BOTH:
                System.out.println("Unknown command.");
                break;
        }
    }
}