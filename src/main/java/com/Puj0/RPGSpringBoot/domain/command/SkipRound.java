package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SkipRound implements ICommand {

    private Acter acter;

    SkipRound(Acter acter) {
        this.acter = acter;
    }

    @Override
    public void execute() {
        log.info("{} decided to skip round.", acter.getName());
    }
}
