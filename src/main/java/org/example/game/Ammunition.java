package org.example.game;

public interface Ammunition {
    static Weapon Sword() {
        return Weapon.builder().addHealth(5).addStrength(2).build();
    }
    static Weapon Shield() {
        return Weapon.builder().addHealth(20).addStrength(-1).addArmor(2).build();
    }
    static Weapon GreatAxe() {
        return Weapon.builder().addHealth(-15).addStrength(5).addArmor(-2).addVampirism(10).build();
    }
    static Weapon Katana() {
        return Weapon.builder().addHealth(-20).addStrength(6).addArmor(-5).addVampirism(50).build();
    }
    static Weapon MagicWand() {
        return Weapon.builder().addHealth(30).addStrength(3).addHealPower(3).build();
    }
}
