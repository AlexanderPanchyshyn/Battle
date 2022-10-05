package org.example.game;

import lombok.SneakyThrows;

import java.security.SecureRandom;
import java.util.Random;

public class SingleSeducer extends Warrior {
    private int seduceValue = 10;
    private int amountOfSeductions = 2;
    private final Army myArmy;
    private final Army enemyArmy;

    public void setSeduceValue(int seduceValue) {
        this.seduceValue = seduceValue;
    }

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

    @SneakyThrows
    private boolean seduce(Army myArmy, Army enemyArmy) {
        Random random = SecureRandom.getInstanceStrong();
        var checker = amountOfSeductions > 0 && seduceValue >= random.nextInt(100);

        if (checker){
            var it = enemyArmy.iterator();
            myArmy.addSeducedUnit(it.next().clone());
            amountOfSeductions--;
        }

        return checker;
    }
}
