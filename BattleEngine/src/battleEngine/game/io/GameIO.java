package battleEngine.game.io;

import battleEngine.data.entities.EnemyData;
import battleEngine.data.entities.PlayerData;

public interface GameIO {
    public class GameIOCooldowns {
        // Cooldown Trackers

        // Player
        private int playerPrimary1Cooldown = 1;
        private int playerSecondary1Cooldown = 1;
        private int playerPrimary2Cooldown = 1;
        private int playerSecondary2Cooldown = 1;
        private int playerEvadeCooldown = 1;
        private int playerUltimateCooldown = 0;
        private int playerHyperModeCooldown = 0;

        // Enemy
        private int enemyPrimary1Cooldown = 1;
        private int enemySecondary1Cooldown = 1;
        private int enemyPrimary2Cooldown = 1;
        private int enemySecondary2Cooldown = 1;
        private int enemyEvadeCooldown = 1;
        private int enemyUltimateCooldown = 0;
        private int enemyHyperModeCooldown = 0;

        public int getPlayerPrimary1Cooldown() { return playerPrimary1Cooldown; }
        public void setPlayerPrimary1Cooldown(int playerPrimary1Cooldown) { this.playerPrimary1Cooldown = playerPrimary1Cooldown; }
        public int getPlayerSecondary1Cooldown() { return playerSecondary1Cooldown; }
        public void setPlayerSecondary1Cooldown(int playerSecondary1Cooldown) { this.playerSecondary1Cooldown = playerSecondary1Cooldown; }
        public int getPlayerPrimary2Cooldown() { return playerPrimary2Cooldown; }
        public void setPlayerPrimary2Cooldown(int playerPrimary2Cooldown) { this.playerPrimary2Cooldown = playerPrimary2Cooldown; }
        public int getPlayerSecondary2Cooldown() { return playerSecondary2Cooldown; }
        public void setPlayerSecondary2Cooldown(int playerSecondary2Cooldown) { this.playerSecondary2Cooldown = playerSecondary2Cooldown; }
        public int getPlayerEvadeCooldown() { return playerEvadeCooldown; }
        public void setPlayerEvadeCooldown(int playerEvadeCooldown) { this.playerEvadeCooldown = playerEvadeCooldown; }
        public int getPlayerUltimateCooldown() { return playerUltimateCooldown; }
        public void setPlayerUltimateCooldown(int playerUltimateCooldown) { this.playerUltimateCooldown = playerUltimateCooldown; }
        public int getPlayerHyperModeCooldown() { return playerHyperModeCooldown; }
        public void setPlayerHyperModeCooldown(int playerHyperModeCooldown) { this.playerHyperModeCooldown = playerHyperModeCooldown; }
        public int getEnemyPrimary1Cooldown() { return enemyPrimary1Cooldown; }
        public void setEnemyPrimary1Cooldown(int enemyPrimary1Cooldown) { this.enemyPrimary1Cooldown = enemyPrimary1Cooldown; }
        public int getEnemySecondary1Cooldown() { return enemySecondary1Cooldown; }
        public void setEnemySecondary1Cooldown(int enemySecondary1Cooldown) { this.enemySecondary1Cooldown = enemySecondary1Cooldown; }
        public int getEnemyPrimary2Cooldown() { return enemyPrimary2Cooldown; }
        public void setEnemyPrimary2Cooldown(int enemyPrimary2Cooldown) { this.enemyPrimary2Cooldown = enemyPrimary2Cooldown; }
        public int getEnemySecondary2Cooldown() { return enemySecondary2Cooldown; }
        public void setEnemySecondary2Cooldown(int enemySecondary2Cooldown) { this.enemySecondary2Cooldown = enemySecondary2Cooldown; }
        public int getEnemyEvadeCooldown() { return enemyEvadeCooldown; }
        public void setEnemyEvadeCooldown(int enemyEvadeCooldown) { this.enemyEvadeCooldown = enemyEvadeCooldown; }
        public int getEnemyUltimateCooldown() { return enemyUltimateCooldown; }
        public void setEnemyUltimateCooldown(int enemyUltimateCooldown) { this.enemyUltimateCooldown = enemyUltimateCooldown; }
        public int getEnemyHyperModeCooldown() { return enemyHyperModeCooldown; }
        public void setEnemyHyperModeCooldown(int enemyHyperModeCooldown) { this.enemyHyperModeCooldown = enemyHyperModeCooldown; }
    }


    public default PlayerData updatePlayer() {
        return null;
    }

    public default EnemyData updateEnemy() {
        return null;
    }

    public default void runGame() {}
}
