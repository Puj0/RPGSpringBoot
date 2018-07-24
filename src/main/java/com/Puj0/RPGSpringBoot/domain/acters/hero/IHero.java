package com.Puj0.RPGSpringBoot.domain.acters.hero;

interface IHero {

    void takeDamage(int damage);

    void replenishHealth();

    RoleClass getRoleClassHero();

    void setRoleClassHero(RoleClass role);
}
