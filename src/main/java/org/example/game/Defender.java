package org.example.game;

public class Defender extends Warrior{
    private int armor = 2;

    public Defender() {
        super(60, 3);
    }

    public int getArmor() {
        return armor;
    }

    protected void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public Warrior equipWeapon(Weapon weapon) {
        setArmor(Math.max(0, getArmor() + weapon.getArmor()));
        return super.equipWeapon(weapon);
    }

    @Override
    public void receiveDamage(HasStrength damager){
        int reducedDamage = Math.max(0, damager.getStrength() - getArmor());
        super.receiveDamage(() -> reducedDamage);
    }
}
