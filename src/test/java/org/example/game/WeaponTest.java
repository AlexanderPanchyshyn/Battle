package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    @DisplayName("Smoke test: Weapons")
    void smokeTest() {
        var ogre = new Warrior();
        var lancelot = new Knight();
        var richard = new Defender();
        var eric = new Vampire();
        var freelancer = new Lancer();
        var priest = new Healer();


        var sword = Ammunition.Sword();
        var shield = Ammunition.Shield();
        var axe = Ammunition.GreatAxe();
        var katana = Ammunition.Katana();
        var wand = Ammunition.MagicWand();
        var superWeapon = Weapon.builder()
                .addHealth(50)
                .addStrength(10)
                .addArmor(5)
                .addVampirism(150)
                .addHealPower(8)
                .build();

        ogre.equipWeapon(sword)
                .equipWeapon(shield)
                .equipWeapon(superWeapon)
                .prepareForBattle();

        lancelot.equipWeapon(superWeapon)
                .prepareForBattle();

        richard.equipWeapon(shield)
                .prepareForBattle();

        eric.equipWeapon(superWeapon)
                .prepareForBattle();

        freelancer.equipWeapon(axe)
                .equipWeapon(katana)
                .prepareForBattle();
        priest.equipWeapon(wand)
                .equipWeapon(shield)
                .prepareForBattle();

        assert ogre.getHealth() == 125;
        assert lancelot.getStrength() == 17;
        assert richard.getArmor() == 4;
        assert eric.getVampirism() == 200;
        assert freelancer.getHealth() == 15;
        assert priest.getHealingValue() == 5;

        assert !Battle.fight(ogre, eric);
        assert !Battle.fight(priest, richard);
        assert Battle.fight(lancelot, freelancer);

        var myArmy = new Army();
        myArmy.addUnits(Knight::new, 1);
        myArmy.addUnits(Lancer::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 1);

        myArmy.equipWarriorAtPosition(0, axe);
        myArmy.equipWarriorAtPosition(1, superWeapon);

        enemyArmy.equipWarriorAtPosition(0, katana);
        enemyArmy.equipWarriorAtPosition(1, wand);

        assert Battle.fight(myArmy, enemyArmy);
    }

    @Test
    void test01() {
        var weapon1 = Weapon.builder()
                .addHealth(-10)
                .addStrength(5)
                .addVampirism(40)
                .build();
        var weapon2 = Ammunition.Sword();
        var unit1 = new Warrior().equipWeapon(weapon1).prepareForBattle();
        var unit2 = new Vampire().equipWeapon(weapon2).prepareForBattle();


        var res = Battle.fight(unit1, unit2);

        assertTrue(res);
    }

    @Test
    void test02() {
        var weapon1 = Ammunition.Shield();
        var weapon2 = Ammunition.GreatAxe();
        var unit1 = new Defender().equipWeapon(weapon1).prepareForBattle();
        var unit2 = new Lancer().equipWeapon(weapon2).prepareForBattle();

        var res = Battle.fight(unit1, unit2);

        assertFalse(res);
    }

    @Test
    void test03() {
        var weapon1 = Ammunition.MagicWand();
        var weapon2 = Ammunition.Katana();
        var unit1 = new Healer().equipWeapon(weapon1).prepareForBattle();
        var unit2 = new Knight().equipWeapon(weapon2).prepareForBattle();

        var res = Battle.fight(unit1, unit2);

        assertFalse(res);
    }

    @Test
    void test04() {
        var weapon1 = Ammunition.Shield();
        var weapon2 = Ammunition.MagicWand();
        var weapon3 = Ammunition.Shield();
        var weapon4 = Ammunition.Katana();
        var unit1 = new Defender().equipWeapon(weapon1).equipWeapon(weapon2).prepareForBattle();
        var unit2 = new Vampire().equipWeapon(weapon3).equipWeapon(weapon4).prepareForBattle();

        var res = Battle.fight(unit1, unit2);

        assertFalse(res);
    }

    @Test
    void test05() {
        var weapon1 = Ammunition.MagicWand();
        var weapon2 = Ammunition.GreatAxe();
        var myArmy = new Army().addUnits(Knight::new, 1).addUnits(Lancer::new, 1);
        var enemyArmy = new Army().addUnits(Vampire::new, 1).addUnits(Healer::new, 1);
        myArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon2);
        enemyArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon2);

        var res = Battle.fight(myArmy, enemyArmy);

        assertTrue(res);
    }

    @Test
    void test06() {
        var weapon1 = Ammunition.Sword();
        var weapon2 = Ammunition.GreatAxe();
        var myArmy = new Army().addUnits(Defender::new, 1).addUnits(Warrior::new, 1);
        var enemyArmy = new Army().addUnits(Knight::new, 1).addUnits(Healer::new, 1);
        myArmy.equipWarriorAtPosition(0, weapon2).equipWarriorAtPosition(1, weapon2);
        enemyArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon1);

        var res = Battle.fight(myArmy, enemyArmy);

        assertTrue(res);
    }

    @Test
    void test07() {
        var weapon1 = Ammunition.Katana();
        var weapon2 = Ammunition.Shield();
        var myArmy = new Army().addUnits(Defender::new, 2);
        var enemyArmy = new Army().addUnits(Knight::new, 1).addUnits(Vampire::new, 1);
        myArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon1);
        enemyArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon1);

        var res = Battle.fight(myArmy, enemyArmy);

        assertFalse(res);
    }

    @Test
    void test08() {
        var weapon1 = Weapon.builder()
                .addHealth(-20)
                .addStrength(6)
                .addArmor(1)
                .addVampirism(40)
                .addHealPower(-2)
                .build();
        var weapon2 = Weapon.builder()
                .addHealth(20)
                .addStrength(-2)
                .addArmor(2)
                .addVampirism(-55)
                .addHealPower(3)
                .build();
        var myArmy = new Army().addUnits(Knight::new, 3);
        var enemyArmy = new Army().addUnits(Warrior::new, 1).addUnits(Defender::new, 2);
        myArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon1).equipWarriorAtPosition(2, weapon2);
        enemyArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon2).equipWarriorAtPosition(2, weapon2);

        var res = Battle.fight(myArmy, enemyArmy);

        assertTrue(res);
    }

    @Test
    void test09() {
        var weapon1 = Weapon.builder()
                .addHealth(-20)
                .addStrength(1)
                .addArmor(1)
                .addVampirism(40)
                .addHealPower(-2)
                .build();
        var weapon2 = Weapon.builder()
                .addHealth(20)
                .addStrength(2)
                .addArmor(2)
                .addVampirism(-55)
                .addHealPower(3)
                .build();
        var myArmy = new Army().addUnits(Vampire::new, 3);
        var enemyArmy = new Army().addUnits(Warrior::new, 1).addUnits(Defender::new, 2);
        myArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon1).equipWarriorAtPosition(2, weapon2);
        enemyArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon2).equipWarriorAtPosition(2, weapon2);

        var res = Battle.straightFight(myArmy, enemyArmy);

        assertFalse(res);
    }

    @Test
    void test10() {
        var weapon1 = Ammunition.Katana();
        var weapon2 = Ammunition.Shield();
        var myArmy = new Army().addUnits(Vampire::new, 2).addUnits(Rookie::new, 2);
        var enemyArmy = new Army().addUnits(Warrior::new, 1).addUnits(Defender::new, 2);
        myArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon1).equipWarriorAtPosition(2, weapon2);
        enemyArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon2).equipWarriorAtPosition(2, weapon2);

        var res = Battle.straightFight(myArmy, enemyArmy);

        assertTrue(res);
    }

    @Test
    void test11() {
        var weapon1 = Ammunition.Sword();
        var weapon2 = Ammunition.GreatAxe();
        var myArmy = new Army().addUnits(Vampire::new, 3);
        var enemyArmy = new Army().addUnits(Warrior::new, 1).addUnits(Defender::new, 2);
        myArmy.equipWarriorAtPosition(0, weapon2).equipWarriorAtPosition(1, weapon2).equipWarriorAtPosition(2, weapon2);
        enemyArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon1);

        var res = Battle.straightFight(myArmy, enemyArmy);

        assertTrue(res);
    }

    @Test
    void test12() {
        var weapon1 = Ammunition.Katana();
        var weapon2 = Ammunition.MagicWand();
        var myArmy = new Army().addUnits(Rookie::new, 3);
        var enemyArmy = new Army().addUnits(Defender::new, 1).addUnits(Healer::new, 1);
        myArmy.equipWarriorAtPosition(0, weapon1).equipWarriorAtPosition(1, weapon1).equipWarriorAtPosition(2, weapon1);
        enemyArmy.equipWarriorAtPosition(0, weapon2).equipWarriorAtPosition(1, weapon2);

        var res = Battle.straightFight(myArmy, enemyArmy);

        assertFalse(res);
    }
}