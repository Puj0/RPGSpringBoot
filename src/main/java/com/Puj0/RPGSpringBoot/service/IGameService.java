package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;

public interface IGameService {

    Iterable<Acter> getAll();

    void createGame(int rounds);

    String startGame(int rounds);
}
