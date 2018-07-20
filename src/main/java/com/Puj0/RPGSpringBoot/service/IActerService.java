package com.Puj0.RPGSpringBoot.service;


import com.Puj0.RPGSpringBoot.domain.acters.Acter;

public interface IActerService {

    public void createActers(int range);

    public Iterable<Acter> allActers();
}
