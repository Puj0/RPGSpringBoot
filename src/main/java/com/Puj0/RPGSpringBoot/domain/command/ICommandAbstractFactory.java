package com.Puj0.RPGSpringBoot.domain.command;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;

public interface ICommandAbstractFactory {

    Attack createAttack(Acter attacker, Acter defender);

    RunAway createRunAway(Acter acter);

    SkipRound createSkipRound(Acter acter);
}
