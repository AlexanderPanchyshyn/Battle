package org.example.game;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class Weapon {
    private int health;
    private int strength;
    private int armor;
    private int vampirism;
    private int healPower;
}
