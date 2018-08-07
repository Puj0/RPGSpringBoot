package com.Puj0.RPGSpringBoot.repository;

import com.Puj0.RPGSpringBoot.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IGameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {
}
