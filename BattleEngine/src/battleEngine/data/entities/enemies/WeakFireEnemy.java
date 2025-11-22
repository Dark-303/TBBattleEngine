package battleEngine.data.entities.enemies;

import battleEngine.data.entities.EnemyData;
import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;

public class WeakFireEnemy extends EnemyData {
    public WeakFireEnemy(double scale) {
        super(
                100,
                7,
                new Armor("Fire Proof T Shirt", 100),
                new Attack("Blade of Flame", 60, 40, 0.70, 0.60, 1, 1),
                new Attack("Candle Slash", 50, 40, 0.40, 0.30, 1, 1),
                new Attack("Tsunami of Fire", 100, 90, 0.80, 0.50, 3, 1),
                1,
                scale);
    }

}