package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunAway implements Command {

    private Acter acter;

    RunAway(Acter acter) { this.acter = acter;}

    @Override
    public void execute() {
        log.info("{} has left the battle. Such a coward.", acter.getName());
    }
}
