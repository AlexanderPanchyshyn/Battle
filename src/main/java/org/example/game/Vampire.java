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
    public void hit(CanReceiveDamage opponent) {
        int damageDealt = hitAndReportDamage(opponent);
        int healMyselfBy = damageDealt * vampirism / 100;
        setHealth(getHealth() + healMyselfBy);
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
}
