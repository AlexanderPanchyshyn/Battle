package org.example;

import org.example.game.*;


public class Main {
    public static void main(String[] args) {
        var army1 = new Army();
        var army2 = new Army().addUnits(Lancer::new, 1).addUnits(Knight::new, 1).addUnits(Warrior::new, 4);

        army1.addUnits(new Seducer(army1, army2), 1);
    }
}