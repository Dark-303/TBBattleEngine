package battleEngine.modes;

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

    public double useAttack() {
        boolean isCrit = false;
        double playerDamage = (float) Math.random()
                * (maxDamage - minDamage)
                + minDamage;
        if (Math.random() < playerData.primaryAttack1CritChance) {
            playerDamage *= 1 + playerData.primaryAttack1CritMultiplier;
            isCrit = true;
        }
    }
}
