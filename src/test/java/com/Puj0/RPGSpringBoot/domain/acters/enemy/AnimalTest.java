package com.Puj0.RPGSpringBoot.domain.acters.enemy;

import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class AnimalTest {

    Animal animal;

    private static final String NAME = "Name";
    private static final int HEALTH_POINTS = 24;
    private static final int ATTACK = 2;
    private static final int DEFENCE = 1;
    private static final int INITIATIVE = 5;

    @BeforeEach
    public void setUp(){
        animal = new Animal(NAME, HEALTH_POINTS, ATTACK, DEFENCE, INITIATIVE);
    }

    @Test
    void defend() {

        final int DAMAGE = 12;

        animal.defend(DAMAGE);

        assertEquals(HEALTH_POINTS - DAMAGE, animal.getHealthPoints());
    }

    @Test
    void replenishHealth_givenAggressive() {
        animal.setAggressive(true);
        animal.replenishHealth();

        assertEquals(HEALTH_POINTS + 1, animal.getHealthPoints());
    }

    @Test
    void replenishHealth_givenNotAggressive() {
        animal.setAggressive(false);
        animal.replenishHealth();

        assertEquals(HEALTH_POINTS, animal.getHealthPoints());
    }

    @Test
    void isMain() {

        Assert.assertFalse(animal.isMain());
    }

}