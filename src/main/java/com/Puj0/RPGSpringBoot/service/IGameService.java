package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.view.GameParameters;
import com.Puj0.RPGSpringBoot.view.GameView;

public interface IGameService {

    GameView getGame(Long id) throws Exception;

    void startGame(GameParameters gameParameters);
}
