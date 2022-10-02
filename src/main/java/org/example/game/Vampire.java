package org.example.game;

public class Vampire extends Warrior implements KnowsDamageDealt {
    private int vampirism  = 50;

    public Vampire() {
        super(40, 4);
    }

    public int getVampirism() {
        return vampirism;
    }

    protected void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }

    @Override
    public Warrior equipWeapon(Weapon weapon) {
        weaponBuilder.addVampirism(weapon.getVampirism());
        return super.equipWeapon(weapon);
    }

    @Override
    protected void onWeaponsEquipped(Weapon weapon) {
        setVampirism(Math.max(0, getVampirism() + weapon.getVampirism()));
        super.onWeaponsEquipped(weapon);
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        int damageDealt = hitAndReportDamage(opponent);
        final int percents = 100;
        int healMyselfBy = damageDealt * vampirism / percents;
        setHealth(getHealth() + healMyselfBy);
    }
}
