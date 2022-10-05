package org.example;

import org.example.game.*;



public class Main {
    public static void main(String[] args) {
        var myArmy = new Army();
        var enemyArmy = new Army().addUnits(Knight::new, 3);
        myArmy.addUnits(new Seducer(myArmy, enemyArmy), 1);
        enemyArmy.equipWarriorAtPosition(0, Ammunition.Sword()).equipWarriorAtPosition(1, Ammunition.Katana());

        System.out.println(Battle.fight(myArmy, enemyArmy));
    }
}