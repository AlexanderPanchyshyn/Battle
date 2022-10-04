package org.example;

import org.example.game.*;



public class Main {
    public static void main(String[] args) {
        var army1 = new Army().addUnits(Healer::new, 1).addUnits(Lancer::new, 2).addUnits(Warlord::new, 1).addUnits(Warrior::new, 1);
        var army2 = new Army().addUnits(Warrior::new, 10);

        Battle.fight(army1, army2);

//        var war1 = new Defender();
//        var war2 = new Vampire();
//
//        war1.equipWeapon(Ammunition.MagicWand()).equipWeapon(Ammunition.Shield()).prepareForBattle();
//        war2.equipWeapon(Ammunition.Katana()).equipWeapon(Ammunition.GreatAxe()).prepareForBattle();
//
//        Battle.fight(war1, war2);
    }
}