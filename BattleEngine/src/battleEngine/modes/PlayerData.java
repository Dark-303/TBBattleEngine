package battleEngine.modes;

public class PlayerData {
    // General Stats
    public double health;
    public double speed;

    // Attacks
    public Attack primaryAttack;    
    public Attack secondaryAttack;
    public Attack ultimateAttack;

    //Weapon
    public Weapon weapon;

    // Hyper Mode
    public double hyperModeMultiplier;
    public int hyperModeDuration;
    public int hyperModeCooldown;

    // Armor Stats
    public String armorName;
    public double armorHP;

    // Evade
    public int evadeCooldown;

    public PlayerData(double health, double speed, Attack primary, Attack secondary, Attack ultimate) {
        this.health = health;
        this.speed = speed;
        this.primaryAttack = primary;
        this.secondaryAttack = secondary;
        this.ultimateAttack = ultimate;
    }

    public PlayerData(double health, double speed, Weapon weapon) {
        this.health = health;
        this.speed = speed;
        this.weapon = weapon;
    }

    public void setGeneralStats(double health, double speed) {
        this.health = health;
        this.speed = speed;
    }

    public void setEvade(int cooldown) {
        this.evadeCooldown = cooldown;
    }

    public void setHyperMode(double multiplier, int duration, int cooldown) {
        hyperModeMultiplier = multiplier;
        hyperModeDuration = duration;
        hyperModeCooldown = cooldown;
    }

    public void setArmor(String name, double hp) {
        armorName = name;
        armorHP = hp;
    }
}