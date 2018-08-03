package com.Puj0.RPGSpringBoot.domain.acters.hero;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;

@Slf4j
@NoArgsConstructor
@Entity
public class Hero extends Acter implements IHero {

    private RoleClass roleClassHero;

    public Hero(int attack, int defence, int healthPoints, int initiative, String name, RoleClass role){
        super();
        super.setName(name);
        super.setHealthPoints(healthPoints);
        super.setAttack(attack);
        super.setDefence(defence);
        super.setInitiative(initiative);
        super.setMain(true);
        this.setRoleClassHero(role);
        log.debug("{}, {}, {} hp, {} att, {} def, {} init.", name, role,
                healthPoints, attack, defence, initiative);
    }

    public RoleClass getRoleClassHero() {
        return roleClassHero;
    }

    public void setRoleClassHero(RoleClass role) {
        this.roleClassHero = role;
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            setHealthPoints(getHealthPoints() - damage);
        }
    }

    @Override
    public void replenishHealth() {
        setHealthPoints(getHealthPoints() + 3);
    }

    @Override
    public boolean isMain() {
        return true;
    }
}
