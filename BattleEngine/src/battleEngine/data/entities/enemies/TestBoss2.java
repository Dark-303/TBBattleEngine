package battleEngine.data.entities.enemies;

import battleEngine.data.entities.EnemyData;
import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;

public class TestBoss2 extends EnemyData {
    private int phase;

    private String phase2Name;
    private int phase2Health;
    private Armor phase2Armor;
    private Attack phase2Primary;
    private Attack phase2Secondary;
    private Attack phase2Ultimate;
    private int phase2Evade;
    private int phase2Speed;
    private String phase3Name;
    private Armor phase3Armor;
    private Attack phase3Primary;
    private Attack phase3Secondary;
    private Attack phase3Ultimate;
    private int phase3Evade;

    public TestBoss2(double scale) {
        super(
                "Tekketsuhana Converge Gundam",
                100,
                7,
                new Armor("Tekketsuhana Armor", 200),
                new Attack("Storm Glaive: Rain and Thunder", 60, 40, 0.70, 0.60, 2, 2),
                new Attack("Dainsleif Rapier: Heaven Piercer", 40, 40, 0.40, 0.30, 1, 1),
                new Attack("Heaven Piercing Storm", 100, 90, 0.80, 0.50, 3, 1),
                1,
                scale);
        
        phase = 1;
        phase2Name = "Steel Lotus Converge Gundam";
        phase2Health = 100;
        phase2Armor = new Armor("Steel Lotus Armor", 250);
        phase2Primary = new Attack("Katana Slash", 65, 65, 0.2, 0.6, 2, 2);
        phase2Secondary = new Attack("Katana Stab", 45, 45, 0.1, 0.6, 1, 1);
        phase2Ultimate = new Attack("Steel Lotus Full Bloom", 120, 100, 0.01, 100, 4, 1);
        phase2Evade = 1;
        phase2Speed = 10;
        /*phase3Armor;
        phase3Primary;
        phase3Secondary;
        phase3Ultimate;
        phase3Evade;*/
    }

    @Override
    public void checkPhase() {
        if (phase == 1 && getArmor().getArmorHP() == 0) {
            System.out.println("PHASE CHANGE:");
            System.out.println("Flowering Lotus Armor Converge!");
            phase = 2;
            setName(phase2Name);
            setHealth(phase2Health);
            setArmor(phase2Armor);
            setPrimaryAttack(phase2Primary);
            setSecondaryAttack(phase2Secondary);
            setUltimateAttack(phase2Ultimate);
            setEvadeCooldown(phase2Evade);
            setSpeed(phase2Speed);

            applyScale();
        }
    }

    @Override
    public boolean checkDamage(int damage, int evasion) {
        damage = damage - evasion;
        if (damage < 0)
            damage = 0;
        if (damage > 0) {
            if (getArmor().getArmorHP() > 0) {
                getArmor().setArmorHP(getArmor().getArmorHP() - damage);
                if (getArmor().getArmorHP() < 0) {
                    setHealth(getHealth() + getArmor().getArmorHP());
                    getArmor().setArmorHP(0);
                }
            } else {
                setHealth(getHealth() - damage);
                if (getHealth() < 0) {
                    setHealth(0);
                }
            }
            checkPhase();
            if (getHealth() <= 0) {
                System.out.println("You have defeated your opponent!");
                return false;
            }
        }
        return true;
    }

    public int getPhase() { return phase; }
    public void setPhase(int phase) { this.phase = phase; }
}
