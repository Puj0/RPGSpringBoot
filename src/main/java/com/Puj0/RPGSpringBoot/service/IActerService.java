package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.ActerSearchRequest;
import com.Puj0.RPGSpringBoot.domain.game.MinimumHeroes;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.view.ActerRequest;
import com.Puj0.RPGSpringBoot.view.ActerView;

import java.util.List;

public interface IActerService {

    List<Acter> createActers(MinimumHeroes acterRange);

    ActerView createActer(ActerRequest acterRequest);

    List<ActerView> getActers(ActerSearchRequest values);
}
