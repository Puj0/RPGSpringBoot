package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.Printer;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Enemy;

public class Attack implements Command{

    private Acter attacker;
    private Acter defender;
    private Printer printer = Printer.getInstance();

    public Attack(Acter attacker, Acter defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public void execute() {

//        if (attacker instanceof Enemy){
//            if (((Enemy) attacker).getAggressive()){
//                doDamage();
//            }
//        } else {
            doDamage();
//        }
    }

    private void doDamage() {
        int damage = Math.max(0, attacker.getAttack() - defender.getDefence());
        defender.defend(damage);
        printer.println(attacker.getName() + " attacked " + defender.getName() + ".");
    }
}
