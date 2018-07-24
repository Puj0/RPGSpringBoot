package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunAway implements Command {

    private Acter acter;
    private static final Logger logger = LoggerFactory.getLogger(RunAway.class);


    RunAway(Acter acter) { this.acter = acter;}

    @Override
    public void execute() {
        logger.info("{} has left the battle. Such a coward.", acter.getName());
    }
}
