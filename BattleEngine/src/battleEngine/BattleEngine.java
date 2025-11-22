package battleEngine;

import battleEngine.Constants.Mode;
import battleEngine.game.Game;

public class BattleEngine{
    public Game game;

    public BattleEngine() {
        Constants.currentMode = Mode.PLAYER;
        Game game = Game.getInstance();
        game.runGame();
    }

}