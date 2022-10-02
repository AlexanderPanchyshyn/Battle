package org.example.game;

import lombok.Getter;

@Getter
public class Weapon {
    private final int health;
    private final int strength;
    private final int armor;
    private final int vampirism;
    private final int healPower;

    Weapon(
            int health,
            int strength,
            int armor,
            int vampirism,
            int healPower
    ) {
        this.health = health;
        this.strength = strength;
        this.armor = armor;
        this.vampirism = vampirism;
        this.healPower = healPower;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int accHealth;
        private int accStrength;
        private int accArmor;
        private int accVampirism;
        private int accHealPower;

        private Builder() {
        }

        public Builder addHealth(int health) {
            accHealth = accHealth + health;
            return this;
        }

        public Builder addStrength(int strength) {
            accStrength = accStrength + strength;
            return this;
        }

        public Builder addArmor(int armor) {
            accArmor = accArmor + armor;
            return this;
        }

        public Builder addVampirism(int vampirism) {
            accVampirism = accVampirism + vampirism;
            return this;
        }

        public Builder addHealPower(int healPower) {
            accHealPower = accHealPower + healPower;
            return this;
        }

        public Weapon build() {
            return new Weapon(
                    accHealth,
                    accStrength,
                    accArmor,
                    accVampirism,
                    accHealPower
            );
        }
    }
}
