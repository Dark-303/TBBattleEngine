package battleEngine.game.io;

import battleEngine.data.entities.PlayerData;
import battleEngine.data.entities.enemies.ScaledFireEnemy;

public interface GameIO {
    public class GameIOCooldowns {
        // Cooldown Trackers

        // Player
        public int playerPrimary1Cooldown = 1;
        public int playerSecondary1Cooldown = 1;
        public int playerPrimary2Cooldown = 1;
        public int playerSecondary2Cooldown = 1;
        public int playerEvadeCooldown = 1;
        public int playerUltimateCooldown = 0;
        public int playerHyperModeCooldown = 0;

        // Enemy
        public int enemyPrimary1Cooldown = 1;
        public int enemySecondary1Cooldown = 1;
        public int enemyPrimary2Cooldown = 1;
        public int enemySecondary2Cooldown = 1;
        public int enemyEvadeCooldown = 1;
        public int enemyUltimateCooldown = 0;
        public int enemyHyperModeCooldown = 0;
    }

    public default PlayerData updatePlayer() {
        return null;
    }

    public default ScaledFireEnemy updateEnemy() {
        return null;
    }
}
