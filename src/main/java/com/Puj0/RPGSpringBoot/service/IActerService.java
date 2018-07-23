package com.Puj0.RPGSpringBoot.service;


import com.Puj0.RPGSpringBoot.domain.acters.Acter;

public interface IActerService {

    void createActers(int range);

    Iterable<Acter> allActers();
}
