package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.Game;

public interface IGameService {

    Game createGame(int rounds);

    String startGame(int rounds);

    String getGameResult(Long id);

}
