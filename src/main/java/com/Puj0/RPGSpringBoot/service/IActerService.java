package com.Puj0.RPGSpringBoot.service;


import com.Puj0.RPGSpringBoot.domain.acters.Acter;

import java.util.List;

public interface IActerService {

    List<Acter> createActers(int range);

    List<Acter> allActers();
}
