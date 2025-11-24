package battleEngine.data.entities;

import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;
import battleEngine.data.models.Weapon;

public class EnemyData {
    public String name;

    // Scale Factor
    public double scaleFactor;

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

    public EnemyData(String name, int health, double speed, Armor armor, Attack primary, Attack secondary, Attack ultimate,
            int evadeCooldown, double scaleFactor) {
        if (name == null) {
            this.name = "Enemy";
        } else {
            this.name = name;
        }
        this.health = (int) (health * scaleFactor);
        this.speed = speed * scaleFactor;
        this.armor = armor;
        this.primaryAttack = primary;
        this.secondaryAttack = secondary;
        this.ultimateAttack = ultimate;
        this.evadeCooldown = evadeCooldown;
        this.scaleFactor = scaleFactor;

        this.armor.armorHP = (int) (this.armor.armorHP * scaleFactor);

        this.primaryAttack.maxDamage = (int) (this.primaryAttack.maxDamage * scaleFactor);
        this.primaryAttack.minDamage = (int) (this.primaryAttack.minDamage * scaleFactor);
        this.primaryAttack.critMultiplier = (int) (this.primaryAttack.critMultiplier * scaleFactor);
        this.primaryAttack.critChance = (int) (this.primaryAttack.critChance * scaleFactor);

        this.secondaryAttack.maxDamage = (int) (this.secondaryAttack.maxDamage * scaleFactor);
        this.secondaryAttack.minDamage = (int) (this.secondaryAttack.minDamage * scaleFactor);
        this.secondaryAttack.critMultiplier = (int) (this.secondaryAttack.critMultiplier * scaleFactor);
        this.secondaryAttack.critChance = (int) (this.secondaryAttack.critChance * scaleFactor);

        this.ultimateAttack.maxDamage = (int) (this.ultimateAttack.maxDamage * scaleFactor);
        this.ultimateAttack.minDamage = (int) (this.ultimateAttack.minDamage * scaleFactor);
        this.ultimateAttack.critMultiplier = (int) (this.ultimateAttack.minDamage * scaleFactor);
        this.ultimateAttack.critChance = (int) (this.ultimateAttack.minDamage * scaleFactor);
    }

    public EnemyData(int health, double speed, Armor armor, Weapon weapon, int evadeCooldown, double scaleFactor) {
        this.health = (int) (health * scaleFactor);
        this.speed = speed * scaleFactor;
        this.armor = armor;
        this.weapon = weapon;
        this.evadeCooldown = evadeCooldown;
        this.scaleFactor = scaleFactor;

        this.armor.armorHP = (int) (this.armor.armorHP * scaleFactor);

        this.weapon.primaryAttack.maxDamage = (int) (this.primaryAttack.maxDamage * scaleFactor);
        this.weapon.primaryAttack.minDamage = (int) (this.primaryAttack.minDamage * scaleFactor);
        this.weapon.primaryAttack.critMultiplier = (int) (this.primaryAttack.critMultiplier * scaleFactor);
        this.weapon.primaryAttack.critChance = (int) (this.primaryAttack.critChance * scaleFactor);

        this.weapon.secondaryAttack.maxDamage = (int) (this.secondaryAttack.maxDamage * scaleFactor);
        this.weapon.secondaryAttack.minDamage = (int) (this.secondaryAttack.minDamage * scaleFactor);
        this.weapon.secondaryAttack.critMultiplier = (int) (this.secondaryAttack.critMultiplier * scaleFactor);
        this.weapon.secondaryAttack.critChance = (int) (this.secondaryAttack.critChance * scaleFactor);

        this.weapon.ultimateAttack.maxDamage = (int) (this.ultimateAttack.maxDamage * scaleFactor);
        this.weapon.ultimateAttack.minDamage = (int) (this.ultimateAttack.minDamage * scaleFactor);
        this.weapon.ultimateAttack.critMultiplier = (int) (this.ultimateAttack.minDamage * scaleFactor);
        this.weapon.ultimateAttack.critChance = (int) (this.ultimateAttack.minDamage * scaleFactor);
    }

    public void setHyperMode(double multiplier, int duration, int cooldown) {
        hyperModeMultiplier = multiplier;
        hyperModeDuration = duration;
        hyperModeCooldown = cooldown;
    }

    public void checkPhase() {
    }

    public void applyScale() {
        health = (int) (health * scaleFactor);
        speed = speed * scaleFactor;

        armor.armorHP = (int) (armor.armorHP * scaleFactor);

        primaryAttack.maxDamage = (int) (primaryAttack.maxDamage * scaleFactor);
        primaryAttack.minDamage = (int) (primaryAttack.minDamage * scaleFactor);
        primaryAttack.critMultiplier = (int) (primaryAttack.critMultiplier * scaleFactor);
        primaryAttack.critChance = (int) (primaryAttack.critChance * scaleFactor);

        secondaryAttack.maxDamage = (int) (secondaryAttack.maxDamage * scaleFactor);
        secondaryAttack.minDamage = (int) (secondaryAttack.minDamage * scaleFactor);
        secondaryAttack.critMultiplier = (int) (secondaryAttack.critMultiplier * scaleFactor);
        secondaryAttack.critChance = (int) (secondaryAttack.critChance * scaleFactor);

        ultimateAttack.maxDamage = (int) (ultimateAttack.maxDamage * scaleFactor);
        ultimateAttack.minDamage = (int) (ultimateAttack.minDamage * scaleFactor);
        ultimateAttack.critMultiplier = (int) (ultimateAttack.minDamage * scaleFactor);
        ultimateAttack.critChance = (int) (ultimateAttack.minDamage * scaleFactor);
    }

    public boolean checkDamage(int damage, int evasion) {
        damage = damage-evasion;
        if (damage < 0) damage = 0;
        if (damage > 0) {
            if (armor.armorHP > 0) {
                armor.armorHP -= damage;
                armor.armorHP = (int) armor.armorHP;
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
                System.out.println("You have defeated your opponent!");
                return false;
            }
        }
        checkPhase();
        return true;
    }
}