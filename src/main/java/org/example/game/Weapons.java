package org.example.game;

public interface Weapons {
    static Weapon Sword() {
        return Weapon.builder().health(5).strength(2).build();
    }
    static Weapon Shield() {
        return Weapon.builder().health(20).strength(-1).armor(2).build();
    }
    static Weapon GreatAxe() {
        return Weapon.builder().health(-15).strength(5).armor(-2).vampirism(10).build();
    }
    static Weapon Katana() {
        return Weapon.builder().health(-20).strength(6).armor(-5).vampirism(50).build();
    }
    static Weapon MagicWand() {
        return Weapon.builder().health(30).strength(3).healPower(3).build();
    }
}
