package com.Puj0.RPGSpringBoot.repository;

import com.Puj0.RPGSpringBoot.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IGameRepository extends JpaRepository<Game, Long> {

    List<Game> findByTotalRounds(int totalRounds);
}
