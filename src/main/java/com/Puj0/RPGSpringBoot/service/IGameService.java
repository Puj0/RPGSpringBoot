package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.GameSearchRequest;
import com.Puj0.RPGSpringBoot.view.GameRequest;
import com.Puj0.RPGSpringBoot.view.GameView;

import java.util.List;

public interface IGameService {

    GameView getGame(Long id);

    void startGame(GameRequest gameRequest);

    List<GameView> getGames(GameSearchRequest request);
}
