package com.Puj0.RPGSpringBoot.repository;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IActerRepository extends JpaRepository<Acter, Long> {

    @Query("select a from Acter a where a.healthPoints > 0")
    List<Acter> findAll();

    List<Acter> findByAttack(int attack);

    List<Acter> findByAttackAndInitiative(int attack, int initiative);
}
