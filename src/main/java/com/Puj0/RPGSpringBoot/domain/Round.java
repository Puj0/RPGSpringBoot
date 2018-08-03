package com.Puj0.RPGSpringBoot.domain;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.ActerWithInitiative;
import com.Puj0.RPGSpringBoot.domain.acters.SortedActersList;
import com.Puj0.RPGSpringBoot.domain.command.Command;
import com.Puj0.RPGSpringBoot.domain.command.CommandAbstractFactory;
import com.Puj0.RPGSpringBoot.domain.command.CommandDispatcher;
import com.Puj0.RPGSpringBoot.domain.command.CommandFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
class Round {

    private SortedActersList acters;
    private ArrayList<Acter> removedActers;
    private CommandDispatcher dispatcher;
    private CommandAbstractFactory commandFactory = new CommandFactory();
    private IRandom random;

    Round(SortedActersList acters, ArrayList<Acter> removedActers, CommandDispatcher dispatcher, IRandom random) {
        this.acters = acters;
        this.removedActers = removedActers;
        this.dispatcher = dispatcher;
        this.random = random;
    }

    void runRound(){
        battle();
        retreat();
        cleanUpBattlefield();
    }

    private void battle() {
        acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acter -> !removedActers.contains(acter))
                .forEach(this::fight);
    }

    private void fight(Acter attacker) {
        if (!isActerAttacking()){
            Command skipRound = commandFactory.createSkipRound(attacker);
            dispatcher.setCommand(skipRound);
            return;
        }

        List<Acter> defenders = createListOfDefender(attacker);

        if (defenders.isEmpty()) {
            log.info("{} had no one to attack.", attacker.getName());
            return;
        }

        attack(attacker, defenders);
    }

    private boolean isActerAttacking() {
        return (random.nextInt(0,4) < 3);
    }

    private List<Acter> createListOfDefender(Acter attacker) {
        return acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(a -> (a.getClass() != attacker.getClass()) && !(removedActers.contains(a)))
                .collect(Collectors.toList());
    }

    private void attack(Acter attacker, List<Acter> defenders) {
        Acter defender = defenders.get(random.nextInt(0, defenders.size()));
        Command attack = commandFactory.createAttack(attacker, defender);
        dispatcher.setCommand(attack);

        if (defender.getHealthPoints() <= 0) {
            killDefender(defender);
        }
    }

    private void killDefender(Acter defender) {
        removedActers.add(defender);
        log.info("{} has died.", defender.getName());
    }

    private void retreat() {
        acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acter -> acter.getHealthPoints() < 2 && !removedActers.contains(acter))
                .forEach(acter -> {
                    Command runAway = commandFactory.createRunAway(acter);
                    dispatcher.setCommand(runAway);
                    removedActers.add(acter);
                });
    }

    private void cleanUpBattlefield() {
        removedActers.forEach(acters::remove);
    }
}
