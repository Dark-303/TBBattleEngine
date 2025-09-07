package battleEngine.modes;

public class PlayerData {
    //General Stats
    public double health;
    public double speed;

    //Primary Attack 1/Weapon 1
    public String weapon1Name;

    public String primaryAttack1Name;
    public double primaryAttack1MaxDamage;
    public double primaryAttack1MinDamage;
    public double primaryAttack1CritChance;
    public double primaryAttack1CritMultiplier;
    public int primaryAttack1Cooldown;

    //Primary Attack 2/Weapon 2
    public String weapon2Name;

    public String primaryAttack2Name;
    public double primaryAttack2MaxDamage;
    public double primaryAttack2MinDamage;
    public double primaryAttack2CritChance;
    public double primaryAttack2CritMultiplier;
    public int primaryAttack2Cooldown;

    //Secondary Attack 1
    public String secondaryAttack1Name;
    public double secondaryAttack1MaxDamage;
    public double secondaryAttack1MinDamage;
    public double secondaryAttack1CritChance;
    public double secondaryAttack1CritMultiplier;
    public int secondaryAttack1Cooldown;

    //Secondary Attack 2
    public String secondaryAttack2Name;
    public double secondaryAttack2MaxDamage;
    public double secondaryAttack2MinDamage;
    public double secondaryAttack2CritChance;
    public double secondaryAttack2CritMultiplier;
    public int secondaryAttack2Cooldown;

    //Ultimate Move
    public String ultimateWeaponName;
    public String ultimateName;
    public double ultimateMaxDamage;
    public double ultimateMinDamage;
    public double ultimateCritChance;
    public double ultimateCritMultiplier;
    public int ultimateCooldown;

    //Hyper Mode
    public double hyperModeMultiplier;
    public int hyperModeDuration;
    public int hyperModeCooldown;

    //Armor Stats
    public String armorName;
    public double armorHP;

    public void setGeneralStats(double health, double speed) {
        this.health = health;
        this.speed = speed;
    }

    public void setPrimaryAttack1(String weaponName, String name, double maxDamage, double minDamage, double CritChance, 
                                    double CritMultiplier, int cooldown) {
        weapon1Name = weaponName;
        primaryAttack1Name = name;
        primaryAttack1MaxDamage = maxDamage;
        primaryAttack1MinDamage = minDamage;
        primaryAttack1CritChance = CritChance;
        primaryAttack1CritMultiplier = CritMultiplier;
        primaryAttack1Cooldown = cooldown;
    }

        public void setPrimaryAttack2(String weaponName, String name, double maxDamage, double minDamage, double CritChance, 
                                    double CritMultiplier, int cooldown) {
        weapon2Name = weaponName;
        primaryAttack2Name = name;
        primaryAttack2MaxDamage = maxDamage;
        primaryAttack2MinDamage = minDamage;
        primaryAttack2CritChance = CritChance;
        primaryAttack2CritMultiplier = CritMultiplier;
        primaryAttack2Cooldown = cooldown;
    }

        public void setSecondaryAttack1(String name, double maxDamage, double minDamage, double CritChance, 
                                    double CritMultiplier, int cooldown) {
        secondaryAttack1Name = name;
        secondaryAttack1MaxDamage = maxDamage;
        secondaryAttack1MinDamage = minDamage;
        secondaryAttack1CritChance = CritChance;
        secondaryAttack1CritMultiplier = CritMultiplier;
        secondaryAttack1Cooldown = cooldown;
    }

            public void setSecondaryAttack2(String name, double maxDamage, double minDamage, double CritChance, 
                                    double CritMultiplier, int cooldown) {
        secondaryAttack2Name = name;
        secondaryAttack2MaxDamage = maxDamage;
        secondaryAttack2MinDamage = minDamage;
        secondaryAttack2CritChance = CritChance;
        secondaryAttack2CritMultiplier = CritMultiplier;
        secondaryAttack2Cooldown = cooldown;
    }

    public void setUltimateMove(String weaponName, String name, double maxDamage, double minDamage, double CritChance, 
                                    double CritMultiplier, int cooldown) {
        ultimateWeaponName = weaponName;
        ultimateName = name;
        ultimateMaxDamage = maxDamage;
        ultimateMinDamage = minDamage;
        ultimateCritChance = CritChance;
        ultimateCritMultiplier = CritMultiplier;
        ultimateCooldown = cooldown;
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
