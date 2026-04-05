package battleEngine.data.models;

public class Attack {
    private String name;
    private double maxDamage;
    private double minDamage;
    private double critChance;
    private double critMultiplier;
    private int cooldown;
    private int currCooldown;


    public Attack(String name, int maxDamage, int minDamage, double CritChance,
            double CritMultiplier, int cooldown, int currCooldown) {
        this.name = name;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.critChance = CritChance;
        this.critMultiplier = CritMultiplier;
        this.cooldown = cooldown;
        this.currCooldown = currCooldown;
    }

    public double useAttack(boolean crit) {
        double playerDamage = (float) Math.random() * (maxDamage - minDamage) + minDamage;
        if (crit) {
            playerDamage *= 1 + critMultiplier;
        }
        return (int) playerDamage;
    }

    public boolean isCriticalHit() {
        double roll = Math.random();
        return roll < critChance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(double maxDamage) {
        this.maxDamage = maxDamage;
    }

    public double getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(double minDamage) {
        this.minDamage = minDamage;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public double getCritMultiplier() {
        return critMultiplier;
    }

    public void setCritMultiplier(double critMultiplier) {
        this.critMultiplier = critMultiplier;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldownValue(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCurrCooldown() {
        return currCooldown;
    }

    public int setCooldown(int cooldown) {
        this.currCooldown = cooldown;
        return this.currCooldown;
    }
}

