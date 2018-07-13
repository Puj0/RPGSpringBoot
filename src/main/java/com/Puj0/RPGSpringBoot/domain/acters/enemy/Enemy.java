package com.Puj0.RPGSpringBoot.domain.acters.enemy;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Enemy extends Acter implements IEnemy {

    private boolean aggressive;

    public void takeDamage(int damage) {
        setHealthPoints(getHealthPoints() - damage);
        if (!aggressive) {
            setAggressive(true);
        }
    }

    @Override
    public boolean isAggressive() {
        return aggressive;
    }

    @Override
    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }

}
