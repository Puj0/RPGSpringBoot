package com.Puj0.RPGSpringBoot.domain.acters.hero;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Hero")
public class Hero extends Acter implements IHero {

    private RoleClass role;

    public Hero(){}

    public Hero(String name, RoleClass role, int healthPoints, int attack, int defence,
                int initiative) {
        super();
        if (name == null) {
            throw new IllegalArgumentException("illegal name: [null]");
        }
        if (healthPoints < 0) {
            throw new IllegalArgumentException("illegal health: [" + healthPoints + "]");
        }
        if (attack < 0) {
            throw new IllegalArgumentException("illegal attack: [" + healthPoints + "]");
        }
        if (defence < 0) {
            throw new IllegalArgumentException("illegal health: [" + defence + "]");
        }
        if (initiative < 0) {
            throw new IllegalArgumentException("illegal attack: [" + initiative + "]");
        }
        super.setName(name);
        super.setHealthPoints(healthPoints);
        super.setAttack(attack);
        super.setDefence(defence);
        super.setInitiative(initiative);
        setRoleClass(role);
        System.out.println(name + ", " + role.toString() + ", " + healthPoints + "hp, "
                + attack + "att, " + defence + "def, " + initiative + "init.");
    }

    @Override
    public RoleClass getRoleClass() {
        return role;
    }

    @Override
    public void setRoleClass(RoleClass role) {
        this.role = role;
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0)
            setHealthPoints(getHealthPoints() - damage);
    }

    @Override
    public void replenishHealth() {
        setHealthPoints(getHealthPoints() + 3);
    }

    @Override
    public void setMain(Boolean main) {
        super.setMain(main);
    }

    @Override
    public Boolean isMain() {
        return Boolean.TRUE;
    }
}
