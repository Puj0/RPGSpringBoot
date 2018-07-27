package com.Puj0.RPGSpringBoot.domain;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.ActerWithInitiative;
import com.Puj0.RPGSpringBoot.domain.acters.SortedActersList;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Troll;
import com.Puj0.RPGSpringBoot.domain.acters.hero.Hero;
import com.Puj0.RPGSpringBoot.domain.command.CommandDispatcher;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingFormatArgumentException;

@Slf4j
@Getter
@Setter
@Entity
public class Game {

    private static final String HEROES_LOST = "Heroes lost!";
    private static final String HEROES_WON = "Heroes were victorious";
    private static final String TIMES_UP = "Time's up!";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String result;

    @Column
    private int totalRounds;

    @Transient
    private SortedActersList acters;

    @Transient
    private ArrayList<Acter> removedActers = new ArrayList<>();

    @Transient
    private int currentRound;

    @Transient
    private CommandDispatcher dispatcher;

    @Transient
    private IRandom random = new ThreadRandom();

    private Game(){}

    private Game(GameBuilder builder) {
        this.acters = builder.acters;
        totalRounds = builder.totalRounds;
        dispatcher = builder.commandDispatcher;
    }

    public String getResult() {
        return result;
    }

    public Game runGame() {
        logCharacterInitiatives();
        runUntilDone(totalRounds);
        return this;
    }

    private void logCharacterInitiatives() {
        for (ActerWithInitiative acterWithInitiative : acters.getArray()) {
            log.info("{} has initiative: {}.", acterWithInitiative.getActer().getName(), acterWithInitiative.getInitiative());
        }
    }

    private void runUntilDone(int rounds) {
        for (currentRound = 0; currentRound < rounds; currentRound++) {
            if (gameDone()) {
                break;
            }
            runRound();
        }

        if (gameDone()) {
            String outcome = outcome();
            if (currentRound == 0) {
                currentRound++;
            }
            log.info("It took {} rounds.", currentRound - 1);
            outcome += "\nIt took " + (currentRound - 1) + " rounds.";
            saveResult(outcome);
        } else {
            log.info(TIMES_UP);
            saveResult(TIMES_UP);
        }
    }

    private void saveResult(String outcome) {
        result = outcome;
    }

    private boolean gameDone() {
        return Arrays.stream(acters.getArray())
                .filter(acterWithInitiative ->
                        acterWithInitiative.
                                getActer().
                                isMain())
                .map(a -> a.getActer().getClass())
                .distinct()
                .count() < 2;
    }

    private void runRound() {
        Round round = new Round(acters, removedActers, dispatcher, random);
        round.runRound();
        logStateAtTheEndOfTheRound();
    }

    private void logStateAtTheEndOfTheRound() {
        log.info("End of round {}. \nHeroes - Trolls - Animals \n{}\t\t{}\t\t{}",
                currentRound + 1, getRaceSize(Hero.class), getRaceSize(Troll.class), getRaceSize(Animal.class));
    }

    private int getRaceSize(Class<? extends Acter> acterClass) {
        return (int) acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acterClass::isInstance)
                .count();
    }

    private String outcome() {
        if (getRaceSize(Hero.class) == 0) {
            log.info(HEROES_LOST);
            saveResult(HEROES_LOST);
            return HEROES_LOST;
        }

        log.info(HEROES_WON);
        saveResult(HEROES_WON);
        return HEROES_WON;
    }

    public static class GameBuilder {
        private SortedActersList acters;
        private int totalRounds;
        private CommandDispatcher commandDispatcher;
        private IRandom random = new ThreadRandom();

        public GameBuilder(int rounds) {
            this.totalRounds = rounds;
            acters = new SortedActersList();
        }

        public GameBuilder addRace(List<Acter> acters) {
            for (Acter acter : acters) {
                this.acters.addActer(new ActerWithInitiative(acter, random));
            }
            return this;
        }

        public GameBuilder addActers(Iterable<Acter> sortedActers) {
            for (Acter acter : sortedActers)
                acters.addActer(new ActerWithInitiative(acter, random));
            return this;
        }

        public GameBuilder addCommandDispatcher(CommandDispatcher commandDispatcher) {
            this.commandDispatcher = commandDispatcher;
            return this;
        }

        public Game build() {
            if (acters == null || commandDispatcher == null) {
                try {
                    throw new MissingFormatArgumentException("Acters, and/or dispatcher is null");
                } catch (Exception e) {
                    log.error("Acters, and/or dispatcher is null",e);
                }
            }
            return new Game(this);
        }
    }
}