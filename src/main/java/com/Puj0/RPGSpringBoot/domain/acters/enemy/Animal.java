package com.Puj0.RPGSpringBoot.domain.acters.enemy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Animal")
public class Animal extends Enemy implements IAnimal {

    public Animal(){}

    public Animal(String name, int healthPoints, int attack, int defence, int initiative) {
        super.setName(name);
        super.setHealthPoints(healthPoints);
        super.setAttack(attack);
        super.setDefence(defence);
        super.setInitiative(initiative);
        super.setAggressive(Boolean.FALSE);
        System.out.println(name + ", " + healthPoints + "hp, " + attack + "att, "
                + defence + "def, " + initiative + "init.");
    }

    @Override
    public void defend(int damage) {
        if (damage < 0) throw new IllegalArgumentException("Damage cannot be negative");
        if (damage > 0) {
            if (this.getHealthPoints() < 10) damage *= 1.15;
            else if (this.getHealthPoints() > 25) damage *= 0.8;
            this.takeDamage(damage);
            setAggressive(true);
        }
    }

    @Override
    public void replenishHealth() {
    }

    @Override
    public Boolean isMain() {
        return Boolean.FALSE;
    }

    @Override
    public String toString() {
        return "Animal{} " + super.toString();
    }
}
