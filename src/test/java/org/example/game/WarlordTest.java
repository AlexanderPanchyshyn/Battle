package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarlordTest {

    @Test
    @DisplayName("Smoke test: Warlord")
    void smokeTest() {
        var ronald = new Warlord();
        var heimdall = new Knight();

        assert !Battle.fight(heimdall, ronald);

        var myArmy = new Army();
        myArmy.addUnits(Warlord::new, 1);
        myArmy.addUnits(Warrior::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 2);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warlord::new, 3);
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 2);
        enemyArmy.addUnits(Knight::new, 2);

        myArmy.moveUnits();
        enemyArmy.moveUnits();

// you can provide any other interface for testing the order
        assert myArmy.unitAtPosition(0).getClass() == Lancer.class;
        assert myArmy.unitAtPosition(1).getClass() == Healer.class;
// negative index means from the last position, so -1 == last
        assert myArmy.unitAtPosition(6).getClass() == Warlord.class;

        assert enemyArmy.unitAtPosition(0).getClass() == Vampire.class;
        assert enemyArmy.unitAtPosition(5).getClass() == Warlord.class;
        assert enemyArmy.unitAtPosition(4).getClass() == Knight.class;

//6, not 8, because only 1 Warlord per army could be
        assert enemyArmy.getSize() == 6;

        assert Battle.fight(myArmy, enemyArmy);
    }

    @Test
    void test01() {
        var myArmy = new Army().addUnits(Warlord::new, 1)
                .addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 2)
                .addUnits(Healer::new, 2);
        var enemyArmy = new Army().addUnits(Warlord::new, 1)
                .addUnits(Vampire::new, 1)
                .addUnits(Healer::new, 2)
                .addUnits(Knight::new, 2);

        var res = Battle.fight(myArmy, enemyArmy);

        assertTrue(res);
    }

    @Test
    void test02() {
        var myArmy = new Army().addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 2)
                .addUnits(Defender::new, 1)
                .addUnits(Warlord::new, 3);
        var enemyArmy = new Army().addUnits(Warlord::new, 2)
                .addUnits(Vampire::new, 1)
                .addUnits(Healer::new, 5)
                .addUnits(Knight::new, 2);

        var res = Battle.fight(myArmy, enemyArmy);

        assertFalse(res);
    }

    @Test
    void test03() {
        var myArmy = new Army().addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 3)
                .addUnits(Defender::new, 1)
                .addUnits(Warlord::new, 4);
        var enemyArmy = new Army().addUnits(Warlord::new, 1)
                .addUnits(Vampire::new, 1)
                .addUnits(Rookie::new, 1)
                .addUnits(Knight::new, 1);
        myArmy.equipWarriorAtPosition(0, Ammunition.Sword());
        enemyArmy.equipWarriorAtPosition(0, Ammunition.Shield());

        var res = Battle.fight(myArmy, enemyArmy);

        assertTrue(res);
    }

    @Test
    void test04() {
        var myArmy = new Army().addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 3)
                .addUnits(Defender::new, 1)
                .addUnits(Warlord::new, 1);
        var enemyArmy = new Army().addUnits(Warlord::new, 5)
                .addUnits(Vampire::new, 1)
                .addUnits(Rookie::new, 1)
                .addUnits(Knight::new, 1);
        myArmy.equipWarriorAtPosition(0, Ammunition.Sword());
        enemyArmy.equipWarriorAtPosition(0, Ammunition.Shield());
        myArmy.moveUnits();
        enemyArmy.moveUnits();

        var res = Battle.straightFight(myArmy, enemyArmy);

        assertFalse(res);
    }
}