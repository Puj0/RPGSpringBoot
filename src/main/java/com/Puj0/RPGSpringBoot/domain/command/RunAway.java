package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.Printer;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;

public class RunAway implements Command {

    private Acter acter;
    private Printer printer = Printer.getInstance();

    public RunAway(Acter acter) { this.acter = acter;}

    @Override
    public void execute() {
        printer.println(acter.getName() + " has left the battle. Such a coward.");
    }
}
