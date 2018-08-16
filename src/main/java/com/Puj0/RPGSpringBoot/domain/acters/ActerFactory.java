package com.Puj0.RPGSpringBoot.domain.acters;

import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Troll;
import com.Puj0.RPGSpringBoot.domain.acters.hero.Hero;
import com.Puj0.RPGSpringBoot.domain.acters.hero.RoleClass;
import com.Puj0.RPGSpringBoot.view.ActerRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActerFactory implements IActerFactory {

    @Override
    public Acter createActer(ActerRequest acterRequest) {
        Acter acter;
        switch (acterRequest.getActerType()) {
            case HERO:
                acter = new Hero(acterRequest.getAttack(), acterRequest.getDefence(), acterRequest.getHealthPoints(), acterRequest.getInitiative(),
                        acterRequest.getName(), RoleClass.values()[acterRequest.getRoleClass() % RoleClass.values().length]);
                break;
            case TROLL:
                acter = new Troll(acterRequest.getName(), acterRequest.getAttack(),
                        acterRequest.getDefence(), acterRequest.getHealthPoints(), acterRequest.getInitiative());
                break;
            case ANIMAL:
                acter = new Animal(acterRequest.getName(), acterRequest.getAttack(),
                        acterRequest.getDefence(), acterRequest.getHealthPoints(), acterRequest.getInitiative());
                break;
            default:
                acter = null;
        }
        return acter;
    }
}
