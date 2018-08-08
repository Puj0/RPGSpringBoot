package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.game.Game;
import com.Puj0.RPGSpringBoot.view.GameRequest;
import com.Puj0.RPGSpringBoot.view.GameView;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IGameService {

    GameView getGame(Long id) throws Exception;

    void startGame(GameRequest gameRequest);

    List<GameView> findByTotalRounds(int totalRounds);
}
