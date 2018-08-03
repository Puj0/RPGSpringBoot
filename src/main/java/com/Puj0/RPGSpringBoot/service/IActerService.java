package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.view.ActerParameters;
import com.Puj0.RPGSpringBoot.view.ActerView;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IActerService {

    List<Acter> createActers(int range);

    List<Acter> getAllActers();

    ResponseEntity<ActerView> createActer(ActerParameters acterParameters);
}
