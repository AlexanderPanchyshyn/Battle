package org.example;

import org.example.game.*;



public class Main {
    public static void main(String[] args) {
        var myArmy = new Army().addUnits(Warlord::new, 1)
                .addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 2)
                .addUnits(Healer::new, 2);
        var enemyArmy = new Army().addUnits(Warlord::new, 3)
                .addUnits(Vampire::new, 1)
                .addUnits(Healer::new, 2)
                .addUnits(Knight::new, 2);

        myArmy.moveUnits();
        enemyArmy.moveUnits();

//        System.out.println(myArmy.unitAtPosition(0).getClass() == Lancer.class);
//        System.out.println(myArmy.unitAtPosition(1).getClass() == Healer.class);
//        System.out.println(myArmy.unitAtPosition(myArmy.getSize() - 1).getClass() == Warlord.class);
//        System.out.println(enemyArmy.unitAtPosition(0).getClass() == Vampire.class);
//        System.out.println(enemyArmy.unitAtPosition(myArmy.getSize()).getClass() == Warlord.class);
////        System.out.println(enemyArmy.unitAtPosition(myArmy.getSize() - 1).getClass() == Knight.class);
//        System.out.println(enemyArmy.getSize() == 6);
        System.out.println(Battle.fight(myArmy, enemyArmy));
    }
}