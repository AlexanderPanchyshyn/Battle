package org.example.game;

public class Warlord extends Warrior {
    private int armor = 2;

    public Warlord() {
        super(100, 4);
    }

    public int getArmor() {
        return armor;
    }

    protected void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public Warrior equipWeapon(Weapon weapon) {
        weaponBuilder.addArmor(weapon.getArmor());
        return super.equipWeapon(weapon);
    }

    @Override
    protected void onWeaponsEquipped(Weapon weapon) {
        setArmor(Math.max(0, getArmor() + weapon.getArmor()));
        super.onWeaponsEquipped(weapon);
    }

    public Army replaceUnits(Army myArmy) {
        var it = myArmy.iterator();
        int armySize = 0;

        for (Warrior ignored : myArmy) {
            armySize++;
        }

        System.out.println("Army size = " + armySize);

        while (true) {
            if (it.next() instanceof Warlord) {
                myArmy.changePosition(it.next(), armySize - 1);
            } else {
                break;
            }
        }

        return myArmy;
    }
}
