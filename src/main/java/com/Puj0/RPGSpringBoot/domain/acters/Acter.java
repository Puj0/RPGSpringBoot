package com.Puj0.RPGSpringBoot.domain.acters;

import com.Puj0.RPGSpringBoot.domain.acters.hero.RoleClass;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "className")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Acter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int healthPoints;
    @Column(nullable = false)
    private int attack;
    @Column(nullable = false)
    private int defence;
    @Column(nullable = false)
    private int initiative;
    private Boolean main;

    @Transient
    private String className;

    @Transient
    public RoleClass roleClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void defend(int damage){
        if (damage > 0) {
            healthPoints -= damage;
        }
    }

    public abstract String getClassName();

    public void setMain(Boolean main){
        this.main = main;
    }

    public Boolean isMain() {
        return main;
    }
}
