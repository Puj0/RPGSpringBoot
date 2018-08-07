package com.Puj0.RPGSpringBoot.repository;

import com.Puj0.RPGSpringBoot.domain.game.GameActer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGameActerRepository extends JpaRepository<GameActer, Long> {
}
