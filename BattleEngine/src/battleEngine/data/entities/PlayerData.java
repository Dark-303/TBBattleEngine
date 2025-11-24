package battleEngine.data.entities;

import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;
import battleEngine.data.models.Weapon;

public class PlayerData {
    // General Stats
    public int health;
    public double speed;

    // Attacks
    public Attack primaryAttack;
    public Attack secondaryAttack;
    public Attack ultimateAttack;

    // Weapon
    public Weapon weapon;

    // Hyper Mode
    public double hyperModeMultiplier;
    public int hyperModeDuration;
    public int hyperModeCooldown;

    // Armor Stats
    public Armor armor;

    // Evade
    public int evadeCooldown;

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

    public boolean checkDamage(double damage) {
        if (damage > 0) {
            if (armor.armorHP > 0) {
                armor.armorHP -= damage;
                if (armor.armorHP < 0) {
                    health += armor.armorHP;
                    armor.armorHP = 0;
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
}