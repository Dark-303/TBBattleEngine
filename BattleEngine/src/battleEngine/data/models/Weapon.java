package battleEngine.data.models;

public class Weapon {
    private String name;
    private Attack primaryAttack;
    private Attack secondaryAttack;
    private Attack ultimateAttack;

    public Weapon(String name, Attack primary, Attack secondary, Attack ultimate) {
        this.name = name;
        this.primaryAttack = primary;
        this.secondaryAttack = secondary;
        this.ultimateAttack = ultimate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attack getPrimaryAttack() {
        return primaryAttack;
    }

    public void setPrimaryAttack(Attack primaryAttack) {
        this.primaryAttack = primaryAttack;
    }

    public Attack getSecondaryAttack() {
        return secondaryAttack;
    }

    public void setSecondaryAttack(Attack secondaryAttack) {
        this.secondaryAttack = secondaryAttack;
    }

    public Attack getUltimateAttack() {
        return ultimateAttack;
    }

    public void setUltimateAttack(Attack ultimateAttack) {
        this.ultimateAttack = ultimateAttack;
    }
}

