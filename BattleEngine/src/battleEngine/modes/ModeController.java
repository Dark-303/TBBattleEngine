package battleEngine.modes;

import battleEngine.modes.player.Player;
import battleEngine.Modes;

public class ModeController {
    public Player player;

    public ModeController(Modes currentMode, PlayerData playerData, PlayerData enemyData) {
        switch(currentMode){
            case Modes.PLAYER:
                System.out.println("Initializing player mode...");
                player = new Player(playerData, enemyData);
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
