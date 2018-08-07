package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Enemy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Attack implements ICommand {

    private Acter attacker;
    private Acter defender;

    public Attack(Acter attacker, Acter defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public void execute() {

        if (attacker instanceof Enemy){
            if (((Enemy) attacker).getAggressive()!= null || ((Enemy) attacker).getAggressive()){
                doDamage();
            }
        } else {
            doDamage();
        }
    }

    private void doDamage() {
        int damage = Math.max(0, attacker.getAttack() - defender.getDefence());
        defender.defend(damage);
        log.info("{} attacked {}.", attacker.getName(), defender.getName());
    }
}
