package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.IRandom;
import com.Puj0.RPGSpringBoot.domain.ThreadRandom;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.ActerWithInitiative;
import com.Puj0.RPGSpringBoot.domain.acters.SortedActersList;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Troll;
import com.Puj0.RPGSpringBoot.domain.acters.hero.Hero;
import com.Puj0.RPGSpringBoot.domain.acters.hero.RoleClass;
import com.Puj0.RPGSpringBoot.repository.ActerRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acterService")
public class ActerService implements IActerService {

    private IRandom random = new ThreadRandom();
    private SortedActersList sortedActers = new SortedActersList();
    private Faker faker = new Faker();

    @Autowired
    private ActerRepository repository;



    @Override
    public void createActers(int range) {
        int numOfHeroes = random.nextInt(range, range * 2 + 1);
        int numOfEnemies = random.nextInt(numOfHeroes, numOfHeroes * 2 + 1);

        createHeroes(numOfHeroes);
        createEnemies(numOfEnemies);
        addActersToDatabase(sortedActers);
    }

    @Override
    public Iterable<Acter> allActers() {
        return repository.findAll();
    }

    private void createHeroes(int numOfHeroes) {
        for (int i = 0; i < numOfHeroes; i++) {
            Hero newHero = new Hero(faker.lordOfTheRings().character(), RoleClass.values()[i % RoleClass.values().length],
                    random.nextInt(10, 26), random.nextInt(3, 11),
                    random.nextInt(2, 7), random.nextInt(1, 10));
            addActerToSortedActers(newHero);
        }
    }

    private void createEnemies(int numOfEnemies) {
        boolean trollDoesNotExist = true;
        for (int i = 0; i < numOfEnemies; i++) {
            boolean isTroll = (trollDoesNotExist) || (random.nextInt(1, 11) % 2) == 1;
            if (isTroll) {
                trollDoesNotExist = false;
                Troll newTroll = new Troll(faker.rickAndMorty().character(), random.nextInt(10, 26),
                        random.nextInt(3, 11), random.nextInt(2, 7),
                        random.nextInt(1, 10));
                addActerToSortedActers(newTroll);
            } else {
                Animal newAnimal = new Animal(faker.pokemon().name(), random.nextInt(5, 16),
                        random.nextInt(0, 6), random.nextInt(2, 5),
                        random.nextInt(1, 5));
                addActerToSortedActers(newAnimal);
            }
        }
    }

    private void addActerToSortedActers(Acter acter) {
        sortedActers.addActer(new ActerWithInitiative(acter, random));
    }

    public void addActersToDatabase(SortedActersList acters){
        for (ActerWithInitiative acter : acters.getArray()) {
            repository.save(acter.getActer());
        }
    }
}
