package battleEngine.data.entities;

import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;
import battleEngine.data.models.Weapon;

public class EnemyData {
    // Scale Factor
    public double scaleFactor;

    // General Stats
    public double health;
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

    public EnemyData(double health, double speed, Armor armor, Attack primary, Attack secondary, Attack ultimate,
            int evadeCooldown, double scaleFactor) {
        this.health = health * scaleFactor;
        this.speed = speed * scaleFactor;
        this.armor = armor;
        this.primaryAttack = primary;
        this.secondaryAttack = secondary;
        this.ultimateAttack = ultimate;
        this.evadeCooldown = evadeCooldown;
        this.scaleFactor = scaleFactor;

        this.armor.armorHP *= scaleFactor;
        this.primaryAttack.maxDamage *= scaleFactor;
        this.primaryAttack.minDamage *= scaleFactor;
        this.primaryAttack.critMultiplier *= scaleFactor;
        this.primaryAttack.critChance *= scaleFactor;

        this.secondaryAttack.maxDamage *= scaleFactor;
        this.secondaryAttack.minDamage *= scaleFactor;
        this.secondaryAttack.critMultiplier *= scaleFactor;
        this.secondaryAttack.critChance *= scaleFactor;

        this.ultimateAttack.maxDamage *= scaleFactor;
        this.ultimateAttack.minDamage *= scaleFactor;
        this.ultimateAttack.critMultiplier *= scaleFactor;
        this.ultimateAttack.critChance *= scaleFactor;
    }

    public EnemyData(double health, double speed, Armor armor, Weapon weapon, int evadeCooldown, double scaleFactor) {
        this.health = health * scaleFactor;
        this.speed = speed * scaleFactor;
        this.armor = armor;
        this.weapon = weapon;
        this.evadeCooldown = evadeCooldown;
        this.scaleFactor = scaleFactor;

        this.armor.armorHP *= scaleFactor;
        this.weapon.primaryAttack.maxDamage *= scaleFactor;
        this.weapon.primaryAttack.minDamage *= scaleFactor;
        this.weapon.primaryAttack.critMultiplier *= scaleFactor;
        this.weapon.primaryAttack.critChance *= scaleFactor;

        this.weapon.secondaryAttack.maxDamage *= scaleFactor;
        this.weapon.secondaryAttack.minDamage *= scaleFactor;
        this.weapon.secondaryAttack.critMultiplier *= scaleFactor;
        this.weapon.secondaryAttack.critChance *= scaleFactor;

        this.weapon.ultimateAttack.maxDamage *= scaleFactor;
        this.weapon.ultimateAttack.minDamage *= scaleFactor;
        this.weapon.ultimateAttack.critMultiplier *= scaleFactor;
        this.weapon.ultimateAttack.critChance *= scaleFactor;
    }

    public void setHyperMode(double multiplier, int duration, int cooldown) {
        hyperModeMultiplier = multiplier;
        hyperModeDuration = duration;
        hyperModeCooldown = cooldown;
    }
}