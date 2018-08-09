package com.Puj0.RPGSpringBoot.view;

import com.Puj0.RPGSpringBoot.domain.acters.ActerType;
import com.Puj0.RPGSpringBoot.domain.acters.hero.RoleClass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActerView {

    private Long id;

    private String name;

    private int healthPoints;

    private int attack;

    private int defence;

    private int initiative;

    private ActerType className;

    private RoleClass roleClass;
}
