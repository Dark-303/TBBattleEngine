package battleEngine.modes;

public class Weapon {
    public String name;
    public Attack primary;
    public Attack secondary;
    public Attack ultimate;

    public Weapon(String name, Attack primary, Attack secondary, Attack ultimate) {
        this.name = name;
        this.primary = primary;
        this.secondary = secondary;
        this.ultimate = ultimate;
    }
}
