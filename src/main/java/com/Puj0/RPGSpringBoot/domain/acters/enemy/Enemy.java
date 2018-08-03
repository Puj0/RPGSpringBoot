package com.Puj0.RPGSpringBoot.domain.acters.enemy;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Enemy extends Acter implements IEnemy {

    private Boolean aggressive = Boolean.FALSE;

    void takeDamage(int damage) {
        setHealthPoints(getHealthPoints() - damage);
        if (aggressive == null || !aggressive) {
            setAggressive(Boolean.TRUE);
        }
    }
}
