package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.Printer;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;

public class SkipRound implements Command {

    private Acter acter;
    private Printer printer = Printer.getInstance();

    public SkipRound(Acter acter) {
        this.acter = acter;
    }

    @Override
    public void execute() {
        printer.println(acter.getName() + " decided to skip round.");
    }
}
