package com.Puj0.RPGSpringBoot.domain.acters.enemy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Troll")
public class Troll extends Enemy implements ITroll {

    private static final Logger logger = LoggerFactory.getLogger(Troll.class);

    public Troll(){}

    public Troll(String name, int healthPoints, int attack, int defence, int initiative) {
        super();
        super.setName(name);
        super.setHealthPoints(healthPoints);
        super.setAttack(attack);
        super.setDefence(defence);
        super.setInitiative(initiative);
        super.setAggressive(Boolean.TRUE);
        super.setMain(true);
        logger.debug("{}, {} hp, {} att, {} def, {} init.", name,
                healthPoints, attack, defence, initiative);
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
