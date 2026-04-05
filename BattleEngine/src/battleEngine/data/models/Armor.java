package battleEngine.data.models;

public class Armor {
    private String name;
    private int armorHP;

    public Armor(String name, int armorHP) {
        this.name = name;
        this.armorHP = armorHP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmorHP() {
        return armorHP;
    }

    public void setArmorHP(int armorHP) {
        this.armorHP = armorHP;
    }
}
