package com.Puj0.RPGSpringBoot.domain.acters;

import com.Puj0.RPGSpringBoot.domain.acters.hero.RoleClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Setter
@Getter
@Entity
@DiscriminatorColumn(name = "className")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
public abstract class Acter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private int attack;

    @Column(nullable = false)
    private int defence;

    @Column(nullable = false, updatable = false)
    private int healthPoints;

    @Column(nullable = false)
    private int initiative;

    private boolean main;

    @Column(nullable = false)
    private String name;

    @Transient
    public RoleClass roleClass;

    @Transient
    private ActerClass className;

    public void defend(int damage){
        if (damage > 0) {
            healthPoints -= damage;
        }
    }

    public ActerClass getClassName(){
        return ActerClass.valueOf(this.getClass().getSimpleName().toUpperCase());
    }

    public RoleClass getRoleClass(){
        return roleClass;
    }
}
