package org.example;

import org.example.game.*;



public class Main {
    public static void main(String[] args) {
//        var army1 = new Army();
//        var army2 = new Army().addUnits(Warrior::new, 4);
//
//        army1.addUnits(new Seducer(army1, army2), 1);
//        army2.equipWarriorAtPosition(0, Ammunition.Shield()).equipWarriorAtPosition(0, Ammunition.Katana());
//
//        Battle.fight(army1, army2);

        var war1 = new Defender();
        var war2 = new Vampire();

        war1.equipWeapon(Ammunition.MagicWand()).equipWeapon(Ammunition.Shield()).prepareForBattle();
        war2.equipWeapon(Ammunition.Katana()).equipWeapon(Ammunition.GreatAxe()).prepareForBattle();

        Battle.fight(war1, war2);
    }
}