package battleEngine.data.entities;

import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;
import battleEngine.data.models.Weapon;

public class PlayerData {
    // General Stats
    private int health;
    private double speed;

    // Attacks
    private Attack primaryAttack;
    private Attack secondaryAttack;
    private Attack ultimateAttack;

    // Weapon
    private Weapon weapon;

    // Hyper Mode
    private double hyperModeMultiplier;
    private int hyperModeDuration;
    private int hyperModeCooldown;

    // Armor Stats
    private Armor armor;

    // Evade
    private int evadeCooldown;

    public PlayerData(int health, double speed, Armor armor, Attack primary, Attack secondary, Attack ultimate,
            int evadeCooldown) {
        this.health = health;
        this.speed = speed;
        this.armor = armor;
        this.primaryAttack = primary;
        this.secondaryAttack = secondary;
        this.ultimateAttack = ultimate;
        this.evadeCooldown = evadeCooldown;
    }

    public PlayerData(int health, double speed, Armor armor, Weapon weapon, int evadeCooldown) {
        this.health = health;
        this.speed = speed;
        this.armor = armor;
        this.weapon = weapon;
        this.evadeCooldown = evadeCooldown;
    }

    public void setHyperMode(double multiplier, int duration, int cooldown) {
        hyperModeMultiplier = multiplier;
        hyperModeDuration = duration;
        hyperModeCooldown = cooldown;
    }

    public boolean checkDamage(int damage, int evasion) {
        damage = damage - evasion;
        if (damage < 0)
            damage = 0;
        if (damage > 0) {
            if (armor.getArmorHP() > 0) {
                armor.setArmorHP(armor.getArmorHP() - damage);
                if (armor.getArmorHP() < 0) {
                    health += armor.getArmorHP();
                    armor.setArmorHP(0);
                }
            } else {
                health -= damage;
                health = (int) health;
                if (health < 0) {
                    health = 0;
                }
            }
            if (health <= 0) {
                System.out.println("You have been defeated!");
                return false;
            }
        }
        return true;
    }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    public Attack getPrimaryAttack() { return primaryAttack; }
    public void setPrimaryAttack(Attack primaryAttack) { this.primaryAttack = primaryAttack; }
    public Attack getSecondaryAttack() { return secondaryAttack; }
    public void setSecondaryAttack(Attack secondaryAttack) { this.secondaryAttack = secondaryAttack; }
    public Attack getUltimateAttack() { return ultimateAttack; }
    public void setUltimateAttack(Attack ultimateAttack) { this.ultimateAttack = ultimateAttack; }
    public Weapon getWeapon() { return weapon; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }
    public double getHyperModeMultiplier() { return hyperModeMultiplier; }
    public void setHyperModeMultiplier(double hyperModeMultiplier) { this.hyperModeMultiplier = hyperModeMultiplier; }
    public int getHyperModeDuration() { return hyperModeDuration; }
    public void setHyperModeDuration(int hyperModeDuration) { this.hyperModeDuration = hyperModeDuration; }
    public int getHyperModeCooldown() { return hyperModeCooldown; }
    public void setHyperModeCooldown(int hyperModeCooldown) { this.hyperModeCooldown = hyperModeCooldown; }
    public Armor getArmor() { return armor; }
    public void setArmor(Armor armor) { this.armor = armor; }
    public int getEvadeCooldown() { return evadeCooldown; }
    public void setEvadeCooldown(int evadeCooldown) { this.evadeCooldown = evadeCooldown; }
}
