package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.game.MinimumHeroes;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.view.ActerRequest;
import com.Puj0.RPGSpringBoot.view.ActerView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IActerService {

    List<Acter> createActers(MinimumHeroes acterRange);

    List<Acter> getAllActers();

    ActerView createActer(ActerRequest acterRequest);

    List<ActerView> findByAttackAndInitiative(int attack, Integer initiative);
}
