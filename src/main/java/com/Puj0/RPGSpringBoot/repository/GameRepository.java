package com.Puj0.RPGSpringBoot.repository;

import com.Puj0.RPGSpringBoot.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
