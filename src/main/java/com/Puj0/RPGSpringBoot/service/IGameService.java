package com.Puj0.RPGSpringBoot.service;

import java.util.List;

public interface IGameService {

    String getGameResult(Long id);

    String startGame(int rounds, List<Long> idList);
}
