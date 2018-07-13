package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;

public interface IGameService {

    public Iterable<Acter> getAll();
    public void createGame();
    public String startGame();

}
