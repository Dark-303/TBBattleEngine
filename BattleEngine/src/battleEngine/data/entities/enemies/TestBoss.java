package battleEngine.data.entities.enemies;

import battleEngine.data.entities.EnemyData;
import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;

public class TestBoss extends EnemyData {
    private int phase;


    private String phase2Name;
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

    public TestBoss(double scale) {
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
            setHealth(getInitHealth());
            setArmor(phase2Armor);
            setPrimaryAttack(phase2Primary);
            setSecondaryAttack(phase2Secondary);
            setUltimateAttack(phase2Ultimate);
            setEvadeCooldown(phase2Evade);
            setSpeed(phase2Speed);

            applyScale();
        }
    }

    public int getPhase() { return phase; }
    public void setPhase(int phase) { this.phase = phase; }
    public String getPhase2Name() { return phase2Name; }
    public void setPhase2Name(String phase2Name) { this.phase2Name = phase2Name; }
    public Armor getPhase2Armor() { return phase2Armor; }
    public void setPhase2Armor(Armor phase2Armor) { this.phase2Armor = phase2Armor; }
    public Attack getPhase2Primary() { return phase2Primary; }
    public void setPhase2Primary(Attack phase2Primary) { this.phase2Primary = phase2Primary; }
    public Attack getPhase2Secondary() { return phase2Secondary; }
    public void setPhase2Secondary(Attack phase2Secondary) { this.phase2Secondary = phase2Secondary; }
    public Attack getPhase2Ultimate() { return phase2Ultimate; }
    public void setPhase2Ultimate(Attack phase2Ultimate) { this.phase2Ultimate = phase2Ultimate; }
    public int getPhase2Evade() { return phase2Evade; }
    public void setPhase2Evade(int phase2Evade) { this.phase2Evade = phase2Evade; }
    public int getPhase2Speed() { return phase2Speed; }
    public void setPhase2Speed(int phase2Speed) { this.phase2Speed = phase2Speed; }
    public String getPhase3Name() { return phase3Name; }
    public void setPhase3Name(String phase3Name) { this.phase3Name = phase3Name; }
    public Armor getPhase3Armor() { return phase3Armor; }
    public void setPhase3Armor(Armor phase3Armor) { this.phase3Armor = phase3Armor; }
    public Attack getPhase3Primary() { return phase3Primary; }
    public void setPhase3Primary(Attack phase3Primary) { this.phase3Primary = phase3Primary; }
    public Attack getPhase3Secondary() { return phase3Secondary; }
    public void setPhase3Secondary(Attack phase3Secondary) { this.phase3Secondary = phase3Secondary; }
    public Attack getPhase3Ultimate() { return phase3Ultimate; }
    public void setPhase3Ultimate(Attack phase3Ultimate) { this.phase3Ultimate = phase3Ultimate; }
    public int getPhase3Evade() { return phase3Evade; }
    public void setPhase3Evade(int phase3Evade) { this.phase3Evade = phase3Evade; }
}