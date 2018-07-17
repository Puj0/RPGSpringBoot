package com.Puj0.RPGSpringBoot.domain.acters.enemy;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Enemy extends Acter implements IEnemy {

    private Boolean aggressive;

    public void takeDamage(int damage) {
        setHealthPoints(getHealthPoints() - damage);
        if (aggressive == null || !aggressive) {
            setAggressive(Boolean.TRUE);
        }
    }

    @Override
    public Boolean isAggressive() {
        return aggressive;
    }

    @Override
    public void setAggressive(Boolean aggressive) {
        this.aggressive = aggressive;
    }

}
