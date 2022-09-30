package org.example.game;

public class Healer extends Warrior implements CanProcessCommand {
    private int healingValue = 2;

    public Healer() {
        super(60, 0);
    }

    public int getHealingValue() {
        return healingValue;
    }

    public void setHealingValue(int healingValue) {
        this.healingValue = healingValue;
    }

    @Override
    public Warrior equipWeapon(Weapon weapon) {
        setInitialHealth(Math.max(0, getInitialHealth() + weapon.getHealth()));
        setHealth(Math.max(0, getHealth() + weapon.getHealth()));
        setHealingValue(Math.max(0, getHealingValue() + weapon.getHealPower()));
        return this;
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        // Nothing
    }

    public void heal(Warrior damagedWarrior) {
        damagedWarrior.setHealth(damagedWarrior.getHealth() + healingValue);
    }

    public void processCommand(Command command, WarriorInArmy sender) {
        if (command instanceof HealCommand) {
            heal(sender.getWrapped());
        }
    }
}
