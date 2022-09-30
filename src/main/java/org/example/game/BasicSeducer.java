package org.example.game;

import lombok.SneakyThrows;

import java.security.SecureRandom;
import java.util.Random;

public class BasicSeducer extends Warrior {
    private static final int STRENGTH = 3;
    private static final int SEDUCE_VALUE = 10;
    private final Army myArmy;
    private final Army enemyArmy;

    public BasicSeducer(Army myArmy, Army enemyArmy){
        super(40);
        this.myArmy = myArmy;
        this.enemyArmy = enemyArmy;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }
    @SneakyThrows
    private boolean seduce(Army myArmy, Army enemyArmy) {
        Random random = SecureRandom.getInstanceStrong();
        int randomResult = random.nextInt(100);

        if (randomResult <= SEDUCE_VALUE){
            var it = enemyArmy.iterator();

            myArmy.addSeducedUnit(it.next().clone());
            enemyArmy.removeSeducedUnit();
        }

        return randomResult <= SEDUCE_VALUE;
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        if (opponent instanceof WarriorInArmy enemy) {
            var attemptToSeduce = seduce(myArmy, enemyArmy);
            if (attemptToSeduce) {
                enemy.getWrapped().setHealth(0);
            } else {
                super.hit(opponent);
            }
        } else {
            super.hit(opponent);
        }
    }
}
