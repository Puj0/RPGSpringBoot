package com.Puj0.RPGSpringBoot.domain;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.ActerWithInitiative;
import com.Puj0.RPGSpringBoot.domain.acters.SortedActersList;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Troll;
import com.Puj0.RPGSpringBoot.domain.acters.hero.Hero;
import com.Puj0.RPGSpringBoot.domain.command.CommandDispatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private final String HEROES_LOST = "Heroes lost!";
    private final String HEROES_WON = "Heroes were victorious";
    private final String TIMES_UP = "Time's up!";

    private SortedActersList acters;
    private ArrayList<Acter> removedActers = new ArrayList<>();
    private int currentRound;
    private int totalRounds;
    private CommandDispatcher dispatcher;
    private Printer printer;
    private IRandom random = new ThreadRandom();

    private Game(GameBuilder builder){
        this.acters = builder.acters;
        totalRounds = builder.totalRounds;
        dispatcher = builder.commandDispatcher;
        printer = builder.printer;
    }

    public String runGame(){
        printCharacterInitiatives();
        return runUntilDone(totalRounds);
    }

    private void printCharacterInitiatives() {
        for (ActerWithInitiative acterWithInitiative : acters.getArray()) {
            printer.println(acterWithInitiative.getActer().getName() +
                    "has initiative: " + acterWithInitiative.getInitiative());
        }
    }

    private String runUntilDone(int rounds) {
        for (currentRound = 0; currentRound < rounds; currentRound++){
            if (gameDone()) {
                break;
            }
            runRound();
        }
        if (gameDone()){
            String outcome = outcome();
            if (currentRound == 0)
                currentRound++;
            printer.println("It took " + (currentRound - 1) + " rounds.");
            return outcome + "\nIt took " + (currentRound - 1) + " rounds.";
        } else {
            printer.println(TIMES_UP);
            return TIMES_UP;
        }
    }

    private boolean gameDone() {
        return Arrays.stream(acters.getArray())
                .filter(acterWithInitiative -> acterWithInitiative.getActer().isMain())
                .map(a -> a.getActer().getClass())
                .distinct()
                .count() < 2;
    }

    private void runRound() {
        Round round = new Round(acters, removedActers, dispatcher, random);
        round.runRound();
        stateAtTheEndOfTheRound();
    }

    private void stateAtTheEndOfTheRound() {
        printer.println("End of round " + (currentRound + 1) + ". \n" +
                "Heroes - Trolls - Animals \n" +
                getRaceSize(Hero.class) + "\t\t" + getRaceSize(Troll.class) + "\t\t" +
                getRaceSize(Animal.class));
    }

    private int getRaceSize(Class<? extends Acter> acterClass) {
        return (int) acters.stream()
                .map(ActerWithInitiative::getActer)
                .filter(acterClass::isInstance)
                .count();
    }

    private String outcome() {
        if (getRaceSize(Hero.class) == 0) {
            printer.println(HEROES_LOST);
            return HEROES_LOST;
            //TODO: addResultToDatabase(HEROES_LOST);
        } else {
            printer.println(HEROES_WON);
            return HEROES_WON;
            //TODO: addResultToDatabase(HEROES_WON);
        }
    }

    public static class GameBuilder {
        private SortedActersList acters;
        private int totalRounds;
        private CommandDispatcher commandDispatcher;
        private Printer printer;
        private IRandom random = new ThreadRandom();

        public GameBuilder(int rounds){
            this.totalRounds = rounds;
            acters = new SortedActersList();
        }
        public GameBuilder addRace(List<Acter> acters) {
            for (Acter acter : acters) {
                this.acters.addActer(new ActerWithInitiative(acter, random));
            }
            return this;
        }

        public GameBuilder addPrinter(Printer printer) {
            this.printer = printer;
            return this;
        }

        public GameBuilder addActers(Iterable<Acter> sortedActers) {
            for (Acter acter : sortedActers)
                acters.addActer(new ActerWithInitiative(acter,random));
            return this;
        }

        public GameBuilder addCommandDispatcher(CommandDispatcher commandDispatcher) {
            this.commandDispatcher = commandDispatcher;
            return this;
        }

        public Game build() {
            if (acters == null || printer == null || commandDispatcher == null) {
                try {
                    throw new Exception("Acters, printer and/or dispatcher is null");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return new Game(this);
        }
    }
}
