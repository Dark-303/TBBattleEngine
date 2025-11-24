package battleEngine.data.entities.enemies;

import battleEngine.data.entities.EnemyData;
import battleEngine.data.models.Armor;
import battleEngine.data.models.Attack;

public class TestBoss2 extends EnemyData {
    int phase;

    String phase2Name;
    int phase2Health;
    Armor phase2Armor;
    Attack phase2Primary;
    Attack phase2Secondary;
    Attack phase2Ultimate;
    int phase2Evade;
    int phase2Speed;
    String phase3Name;
    Armor phase3Armor;
    Attack phase3Primary;
    Attack phase3Secondary;
    Attack phase3Ultimate;
    int phase3Evade;

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
        if (phase == 1 && armor.armorHP == 0) {
            System.out.println("PHASE CHANGE:");
            System.out.println("Flowering Lotus Armor Converge!");
            phase = 2;
            name = phase2Name;
            health = phase2Health;
            armor = phase2Armor;
            primaryAttack = phase2Primary;
            secondaryAttack = phase2Secondary;
            ultimateAttack = phase2Ultimate;
            evadeCooldown = phase2Evade;
            speed = phase2Speed;

            applyScale();
        }
    }

    @Override
    public boolean checkDamage(double damage) {
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
            checkPhase();
            if (health <= 0) {
                System.out.println("You have defeated your opponent!");
                return false;
            }
        }
        return true;
    }
}