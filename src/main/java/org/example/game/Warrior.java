package org.example.game;


public class Warrior implements Cloneable, HasHealth, HasStrength, CanReceiveDamage {
    private int strength;
    private int health;
    private int initialHealth;

    protected final Weapon.Builder weaponBuilder = Weapon.builder();

    protected Warrior(int health, int strength) {
        initialHealth = this.health = health;
        this.strength = strength;
    }

    public Warrior() {
        this(50, 5);
    }

    @Override
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    protected void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    public void receiveDamage(HasStrength damager) {
        setHealth(getHealth() - damager.getStrength());
    }

    public Warrior equipWeapon(Weapon weapon) {
        weaponBuilder
                .addHealth(weapon.getHealth())
                .addStrength(weapon.getStrength());
        return this;
    }

    protected void onWeaponsEquipped(Weapon weapon) {
        setInitialHealth(Math.max(1, getInitialHealth() + weapon.getHealth()));
        setHealth(Math.max(1, getHealth() + weapon.getHealth()));
        setStrength(Math.max(0, getStrength() + weapon.getStrength()));
    }

    public final Warrior prepareForBattle() {
        Weapon weapon = weaponBuilder.build();
        onWeaponsEquipped(weapon);
        return this;
    }

    @Override
    public Warrior clone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException e) {
            // Nothing
        }
        throw new IllegalStateException("Never should get here!");
    }
}
