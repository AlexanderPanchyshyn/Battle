package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeducerTest {

    @Test
    @DisplayName("Seducer vs Warrior")
    void test01() {
        var unit1 = new SingleSeducer();
        var unit2 = new Warrior();

        var res = Battle.fight(unit1, unit2);

        assertFalse(res);
    }

    @Test
    @DisplayName("Seducer with weapon vs Warrior")
    void test02() {
        var weapon = Weapon.builder().addHealth(10).addStrength(2).addArmor(-4).build();
        var unit1 = new SingleSeducer().equipWeapon(weapon).prepareForBattle();
        var unit2 = new Warrior();

        var res = Battle.fight(unit1, unit2);

        assertTrue(res);
    }

    @Test
    @DisplayName("Seducer against Army v.1")
    void test03() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(new Seducer(army1, army2), 1);
        army2.addUnits(Knight::new, 4);

        var warrior = army1.iterator().next();
        if (warrior instanceof SingleSeducer seducer) {
            seducer.setSeduceValue(100);
        }

        var res = Battle.fight(army1, army2);

        assertTrue(res);
    }

    @Test
    @DisplayName("Seducer against Army v.2")
    void test04() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(new Seducer(army1, army2), 1);
        army2.addUnits(Lancer::new, 1).addUnits(Healer::new, 1).addUnits(Vampire::new, 6);

        var warrior = army1.iterator().next();
        if (warrior instanceof SingleSeducer seducer) {
            seducer.setSeduceValue(100);
        }

        var res = Battle.fight(army1, army2);

        assertTrue(res);
    }

    @Test
    @DisplayName("Seducer against Army with weapon")
    void test05() {
        var army1 = new Army();
        var army2 = new Army().addUnits(Knight::new, 3);
        army1.addUnits(new Seducer(army1, army2), 1);
        army2.equipWarriorAtPosition(0, Ammunition.Sword()).equipWarriorAtPosition(1, Ammunition.Katana());

        var warrior = army1.iterator().next();
        if (warrior instanceof SingleSeducer seducer) {
            seducer.setSeduceValue(100);
        }

        var res = Battle.fight(army1, army2);

        assertTrue(res);
        assertEquals(9, army1.unitAtPosition(0).getStrength());
        assertEquals(30, army1.unitAtPosition(1).getHealth());
    }

    @Test
    @DisplayName("Seducer in straight fight")
    void test06() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(new Seducer(army1, army2), 1).addUnits(Warrior::new, 2);
        army2.addUnits(Defender::new, 2);

        var warrior = army1.iterator().next();
        if (warrior instanceof SingleSeducer seducer) {
            seducer.setSeduceValue(100);
        }

        var res = Battle.straightFight(army1, army2);

        assertFalse(res);
    }
}