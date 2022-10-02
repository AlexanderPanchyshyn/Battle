package org.example.game;

import lombok.SneakyThrows;

import java.security.SecureRandom;
import java.util.Random;

public class SingleSeducer extends Warrior {
    private static final int SEDUCE_VALUE = 10;
    private final Army myArmy;
    private final Army enemyArmy;

    public SingleSeducer() {
        super(40, 3);
        this.myArmy = null;
        this.enemyArmy = null;
    }

    public SingleSeducer(Army myArmy, Army enemyArmy){
        super(40, 3);
        this.myArmy = myArmy;
        this.enemyArmy = enemyArmy;
    }

    @SneakyThrows
    private boolean seduce(Army myArmy, Army enemyArmy) {
        Random random = SecureRandom.getInstanceStrong();
        int randomResult = random.nextInt(100);

        if (randomResult <= SEDUCE_VALUE){
            var it = enemyArmy.iterator();
            myArmy.addSeducedUnit(it.next().clone());
        }

        return randomResult <= SEDUCE_VALUE;
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        if (opponent instanceof WarriorInArmy enemy) {
            var attemptToSeduce = seduce(myArmy, enemyArmy);
            if (attemptToSeduce) {
                System.out.println("Yes");
                enemy.getWrapped().setHealth(0);
            } else {
                super.hit(opponent);
            }
        } else {
            super.hit(opponent);
        }
    }
}
