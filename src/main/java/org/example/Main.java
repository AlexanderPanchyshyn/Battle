package org.example;

import org.example.game.*;



public class Main {
    public static void main(String[] args) {
        var army1 = new Army().addUnits(Warrior::new, 4);
        var army2 = new Army().addUnits(Warrior::new, 4);
        army2.equipWarriorAtPosition(1, Ammunition.Shield()).equipWarriorAtPosition(2, Ammunition.Katana()).equipWarriorAtPosition(2, Ammunition.Shield());

        var war1 = new Warrior();
        var war2 = new Lancer();
        System.out.printf("Initial health = %d, health = %d, strength = %d.%n", war1.getInitialHealth(), war1.getHealth(), war1.getStrength());

        war1.equipWeapon(Ammunition.Katana()).equipWeapon(Ammunition.Shield());

        System.out.printf("Initial health = %d, health = %d, strength = %d.%n", war1.getInitialHealth(), war1.getHealth(), war1.getStrength());



        Battle.fight(army1, army2);
    }
}