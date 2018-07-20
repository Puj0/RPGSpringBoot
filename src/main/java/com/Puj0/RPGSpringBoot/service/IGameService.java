package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.Game;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;

import java.util.Optional;

public interface IGameService {

    Iterable<Acter> getAll();

    void createGame(int rounds);

    String startGame(int rounds);

    Optional<Game> getGame(Long id);

    void saveGame();
}
