package battleEngine.data.models;

public class Weapon {
    public String name;
    public Attack primaryAttack;
    public Attack secondaryAttack;
    public Attack ultimateAttack;

    public Weapon(String name, Attack primary, Attack secondary, Attack ultimate) {
        this.name = name;
        this.primaryAttack = primary;
        this.secondaryAttack = secondary;
        this.ultimateAttack = ultimate;
    }
}
