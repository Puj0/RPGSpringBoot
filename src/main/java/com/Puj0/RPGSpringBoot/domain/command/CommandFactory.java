package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;

public class CommandFactory implements CommandAbstractFactory {
    @Override
    public Attack createAttack(Acter attacker, Acter defender) {
        return new Attack(attacker, defender);
    }

    @Override
    public RunAway createRunAway(Acter acter) {
        return new RunAway(acter);
    }

    @Override
    public SkipRound createSkipRound(Acter acter) {
        return new SkipRound(acter);
    }
}
