package battleEngine.data.models;

public class Attack {
    public String name;
    public double maxDamage;
    public double minDamage;
    public double critChance;
    public double critMultiplier;
    public double cooldown;

    public Attack(String name, double maxDamage, double minDamage, double CritChance,
            double CritMultiplier, int cooldown) {
        this.name = name;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.critChance = CritChance;
        this.critMultiplier = CritMultiplier;
        this.cooldown = cooldown;
    }

    public double useAttack(boolean crit) {
        double playerDamage = (float) Math.random()
                * (maxDamage - minDamage)
                + minDamage;
        if (crit) {
            playerDamage *= 1 + critMultiplier;
        }
        return playerDamage;
    }

    public boolean isCriticalHit() {
        double roll = Math.random() * 100;
        return roll < critChance;
    }
}
