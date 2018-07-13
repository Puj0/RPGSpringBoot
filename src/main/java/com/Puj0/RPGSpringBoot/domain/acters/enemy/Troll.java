package com.Puj0.RPGSpringBoot.domain.acters.enemy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Troll")
public class Troll extends Enemy implements ITroll {
    Troll(String name, int healthPoints, int attack, int defence, int initiative, boolean aggressive) {
        super();
        super.setName(name);
        super.setHealthPoints(healthPoints);
        super.setAttack(attack);
        super.setDefence(defence);
        super.setInitiative(initiative);
        super.setAggressive(aggressive);
        System.out.println(name + ", " + healthPoints + "hp, " + attack + "att, " + defence
                + "def, " + initiative + "init.");
    }

    @Override
    public void defend(int damage) {
        if (damage < 0) throw new IllegalArgumentException("Damage cannot be negative");
        if (damage > 0) {
            if (this.getHealthPoints() > 5) damage *= 0.7;
            this.takeDamage(damage);
        }
    }

    @Override
    public void replenishHealth() {
        setHealthPoints(getHealthPoints() + 2);
    }


    @Override
    public boolean isMain() {
        return true;
    }
}
