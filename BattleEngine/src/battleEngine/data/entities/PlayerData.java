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
    public Armor armor;

    // Evade
    public int evadeCooldown;

    public PlayerData(double health, double speed, Armor armor, Attack primary, Attack secondary, Attack ultimate, int evadeCooldown) {
        this.health = health;
        this.speed = speed;
        this.armor = armor;
        this.primaryAttack = primary;
        this.secondaryAttack = secondary;
        this.ultimateAttack = ultimate;
        this.evadeCooldown = evadeCooldown;
    }

    public PlayerData(double health, double speed, Armor armor, Weapon weapon, int evadeCooldown) {
        this.health = health;
        this.speed = speed;
        this.armor = armor;
        this.weapon = weapon;
        this.evadeCooldown = evadeCooldown;
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
}