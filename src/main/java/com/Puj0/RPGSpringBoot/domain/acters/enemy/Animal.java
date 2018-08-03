package com.Puj0.RPGSpringBoot.domain.acters.enemy;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Slf4j
@NoArgsConstructor
@Entity
public class Animal extends Enemy implements IAnimal {

    public Animal(String name, int healthPoints, int attack, int defence, int initiative) {
        super.setName(name);
        super.setHealthPoints(healthPoints);
        super.setAttack(attack);
        super.setDefence(defence);
        super.setInitiative(initiative);
        super.setAggressive(Boolean.FALSE);
        super.setMain(false);
        log.debug("{}, {} hp, {} att, {} def, {} init.", name, healthPoints,
                attack, defence, initiative);
    }

    @Override
    public void defend(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        if (damage > 0) {
            if (this.getHealthPoints() < 10) damage *= 1.15;
            else if (this.getHealthPoints() > 25) damage *= 0.8;
            this.takeDamage(damage);
            setAggressive(true);
        }
    }

    @Override
    public void replenishHealth() {
        if (getAggressive()){
            setHealthPoints(getHealthPoints() + 1);
        }
    }

    @Override
    public boolean isMain() {
        return false;
    }

    @Override
    public String toString() {
        return "Animal{} " + super.toString();
    }
}
