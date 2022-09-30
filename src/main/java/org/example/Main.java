package org.example;

import org.example.game.*;



public class Main {
    public static void main(String[] args) {
        var army1 = new Army();
        var army2 = new Army().addUnits(Lancer::new, 1).addUnits(Knight::new, 1).addUnits(Warrior::new, 4);
        army1.addUnits(new Seducer(army1, army2), 1);

        var war1 = new Defender();
        System.out.printf("Initial health = %d, health = %d, strength = %d, armor = %d.%n", war1.getInitialHealth(), war1.getHealth(), war1.getStrength(), war1.getArmor());
        war1.equipWeapon(Weapons.Katana()).equipWeapon(Weapons.Shield());
        System.out.printf("Initial health = %d, health = %d, strength = %d, armor = %d.%n", war1.getInitialHealth(), war1.getHealth(), war1.getStrength(), war1.getArmor());

        var war2 = new Lancer();

        Battle.fight(war1, war2);
    }
}