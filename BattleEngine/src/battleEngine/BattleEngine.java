package battleEngine;

import battleEngine.game.Game;

public class BattleEngine {
    public Modes currentMode;
    public Game game;

    public BattleEngine() {
        currentMode = Modes.PLAYER;
        Game game = Game.getInstance();
        game.runGame();
    }

}