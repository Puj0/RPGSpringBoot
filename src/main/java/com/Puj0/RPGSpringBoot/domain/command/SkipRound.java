package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkipRound implements Command {

    private Acter acter;
    private final static Logger logger = LoggerFactory.getLogger(SkipRound.class);

    SkipRound(Acter acter) {
        this.acter = acter;
    }

    @Override
    public void execute() {
        logger.info("{} decided to skip round.", acter.getName());
    }
}
