package com.Puj0.RPGSpringBoot.domain.acters.hero;

public interface IHero {

    void takeDamage(int damage);

    void replenishHealth();

    RoleClass getRoleClass();

    void setRoleClass(RoleClass role);

    boolean isMain();
}
