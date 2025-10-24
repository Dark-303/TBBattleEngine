package battleEngine.data.entities.enemies;

import battleEngine.data.entities.EnemyData;
import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;

public class ScaledFireEnemy {
        private EnemyData enemyData;
        private Attack primary;
        private Attack secondary;
        private Attack ultimate;
        private Armor armor;
        private double strengthMultiplier = 1;

        // Constructor
        public ScaledFireEnemy(double multiplier) {
                strengthMultiplier = multiplier;
                primary = new Attack("Blade of Flame", 60.00 * strengthMultiplier, 40.00 * strengthMultiplier,
                                0.70 * strengthMultiplier, 0.60 * strengthMultiplier, 1);
                secondary = new Attack("Candle Slash", 50.00 * strengthMultiplier, 40.00 * strengthMultiplier,
                                0.40 * strengthMultiplier, 0.30 * strengthMultiplier, 1);
                ultimate = new Attack("Tsunami of Fire", 100.00 * strengthMultiplier, 90.00 * strengthMultiplier,
                                0.80 * strengthMultiplier, 0.5 * strengthMultiplier, 3);
                armor = new Armor("Fire Proof T Shirt", 100 * strengthMultiplier);
                enemyData = new EnemyData(100 * strengthMultiplier, 7 * strengthMultiplier, armor, primary, secondary,
                                ultimate,
                                1);
        }

        public EnemyData getEnemyData() {
                return enemyData;
        }
}