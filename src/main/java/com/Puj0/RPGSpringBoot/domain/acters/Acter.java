package com.Puj0.RPGSpringBoot.domain.acters;

import com.Puj0.RPGSpringBoot.domain.acters.hero.RoleClass;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
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
//    @Column
    private boolean main;

    @Transient
    private String className;

    @Transient
    public RoleClass roleClass;

    public void defend(int damage){
        if (damage > 0) {
            healthPoints -= damage;
        }
    }

    public String getClassName(){
        return this.getClass().toString();
    }

}
