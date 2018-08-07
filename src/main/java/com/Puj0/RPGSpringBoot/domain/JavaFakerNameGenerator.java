package com.Puj0.RPGSpringBoot.domain;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class JavaFakerNameGenerator implements INameGenerator {

    private Faker faker = new Faker();

    @Override
    public String getHeroName() {
        return faker.superhero().name();
    }

    @Override
    public String getTrollName() {
        return faker.rickAndMorty().character();
    }

    @Override
    public String getAnimalName() {
        return faker.pokemon().name();
    }
}
