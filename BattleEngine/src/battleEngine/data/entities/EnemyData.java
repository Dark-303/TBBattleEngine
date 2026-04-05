package battleEngine.data.entities;

import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;
import battleEngine.data.models.Weapon;

public class EnemyData {
    private String name;

    // Scale Factor
    private double scaleFactor;

    // General Stats
    private int health;
    private double speed;
    private int initHealth;

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


    public EnemyData(String name, int health, double speed, Armor armor, Attack primary,
            Attack secondary, Attack ultimate, int evadeCooldown, double scaleFactor) {
        if (name == null) {
            this.name = "Enemy";
        } else {
            this.name = name;
        }
        this.health = (int) (health * scaleFactor);
        this.initHealth = health;
        this.speed = speed * scaleFactor;
        this.armor = armor;
        this.primaryAttack = primary;
        this.secondaryAttack = secondary;
        this.ultimateAttack = ultimate;
        this.evadeCooldown = evadeCooldown;
        this.scaleFactor = scaleFactor;

        this.armor.setArmorHP((int) (this.armor.getArmorHP() * scaleFactor));

        this.primaryAttack.setMaxDamage((int) (this.primaryAttack.getMaxDamage() * scaleFactor));
        this.primaryAttack.setMinDamage((int) (this.primaryAttack.getMinDamage() * scaleFactor));
        this.primaryAttack.setCritMultiplier(this.primaryAttack.getCritMultiplier() * scaleFactor);
        this.primaryAttack.setCritChance(this.primaryAttack.getCritChance() * scaleFactor);

        this.secondaryAttack.setMaxDamage((int) (this.secondaryAttack.getMaxDamage() * scaleFactor));
        this.secondaryAttack.setMinDamage((int) (this.secondaryAttack.getMinDamage() * scaleFactor));
        this.secondaryAttack.setCritMultiplier(this.secondaryAttack.getCritMultiplier() * scaleFactor);
        this.secondaryAttack.setCritChance(this.secondaryAttack.getCritChance() * scaleFactor);

        this.ultimateAttack.setMaxDamage((int) (this.ultimateAttack.getMaxDamage() * scaleFactor));
        this.ultimateAttack.setMinDamage((int) (this.ultimateAttack.getMinDamage() * scaleFactor));
        this.ultimateAttack.setCritMultiplier(this.ultimateAttack.getCritMultiplier() * scaleFactor);
        this.ultimateAttack.setCritChance(this.ultimateAttack.getCritChance() * scaleFactor);
    }

    public EnemyData(int health, double speed, Armor armor, Weapon weapon, int evadeCooldown,
            double scaleFactor) {
        this.health = (int) (health * scaleFactor);
        this.initHealth = health;
        this.speed = speed * scaleFactor;
        this.armor = armor;
        this.weapon = weapon;
        this.evadeCooldown = evadeCooldown;
        this.scaleFactor = scaleFactor;

        this.armor.setArmorHP((int) (this.armor.getArmorHP() * scaleFactor));

        this.weapon.getPrimaryAttack().setMaxDamage(
                (int) (this.weapon.getPrimaryAttack().getMaxDamage() * scaleFactor));
        this.weapon.getPrimaryAttack().setMinDamage(
                (int) (this.weapon.getPrimaryAttack().getMinDamage() * scaleFactor));
        this.weapon.getPrimaryAttack().setCritMultiplier(
                this.weapon.getPrimaryAttack().getCritMultiplier() * scaleFactor);
        this.weapon.getPrimaryAttack().setCritChance(this.weapon.getPrimaryAttack().getCritChance() * scaleFactor);

        this.weapon.getSecondaryAttack().setMaxDamage(
                (int) (this.weapon.getSecondaryAttack().getMaxDamage() * scaleFactor));
        this.weapon.getSecondaryAttack().setMinDamage(
                (int) (this.weapon.getSecondaryAttack().getMinDamage() * scaleFactor));
        this.weapon.getSecondaryAttack().setCritMultiplier(
                this.weapon.getSecondaryAttack().getCritMultiplier() * scaleFactor);
        this.weapon.getSecondaryAttack().setCritChance(
                this.weapon.getSecondaryAttack().getCritChance() * scaleFactor);

        this.weapon.getUltimateAttack().setMaxDamage(
                (int) (this.weapon.getUltimateAttack().getMaxDamage() * scaleFactor));
        this.weapon.getUltimateAttack().setMinDamage(
                (int) (this.weapon.getUltimateAttack().getMinDamage() * scaleFactor));
        this.weapon.getUltimateAttack().setCritMultiplier(
                this.weapon.getUltimateAttack().getCritMultiplier() * scaleFactor);
        this.weapon.getUltimateAttack().setCritChance(
                this.weapon.getUltimateAttack().getCritChance() * scaleFactor);
    }


    public void setHyperMode(double multiplier, int duration, int cooldown) {
        hyperModeMultiplier = multiplier;
        hyperModeDuration = duration;
        hyperModeCooldown = cooldown;
    }

    public void checkPhase() {}

    public void applyScale() {
        health = (int) (health * scaleFactor);

        armor.setArmorHP((int) (armor.getArmorHP() * scaleFactor));

        primaryAttack.setMaxDamage((int) (primaryAttack.getMaxDamage() * scaleFactor));
        primaryAttack.setMinDamage((int) (primaryAttack.getMinDamage() * scaleFactor));
        primaryAttack.setCritMultiplier(primaryAttack.getCritMultiplier() * scaleFactor);
        primaryAttack.setCritChance(primaryAttack.getCritChance() * scaleFactor);

        secondaryAttack.setMaxDamage((int) (secondaryAttack.getMaxDamage() * scaleFactor));
        secondaryAttack.setMinDamage((int) (secondaryAttack.getMinDamage() * scaleFactor));
        secondaryAttack.setCritMultiplier(secondaryAttack.getCritMultiplier() * scaleFactor);
        secondaryAttack.setCritChance(secondaryAttack.getCritChance() * scaleFactor);

        ultimateAttack.setMaxDamage((int) (ultimateAttack.getMaxDamage() * scaleFactor));
        ultimateAttack.setMinDamage((int) (ultimateAttack.getMinDamage() * scaleFactor));
        ultimateAttack.setCritMultiplier(ultimateAttack.getCritMultiplier() * scaleFactor);
        ultimateAttack.setCritChance(ultimateAttack.getCritChance() * scaleFactor);
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
                System.out.println("You have defeated your opponent!");
                return false;
            }
        }
        checkPhase();
        return true;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getScaleFactor() { return scaleFactor; }
    public void setScaleFactor(double scaleFactor) { this.scaleFactor = scaleFactor; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    public int getInitHealth() { return initHealth; }
    public void setInitHealth(int initHealth) { this.initHealth = initHealth; }
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

