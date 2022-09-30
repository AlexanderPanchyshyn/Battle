package org.example.game;

import java.util.function.Supplier;

public class Seducer implements Supplier<Warrior> {

    private final Army myArmy;
    private final Army enemyArmy;

    public Seducer(Army myArmy, Army enemyArmy) {
        this.myArmy = myArmy;
        this.enemyArmy = enemyArmy;
    }

    public Warrior get() {
        return new SingleSeducer(myArmy, enemyArmy);
    }
}
