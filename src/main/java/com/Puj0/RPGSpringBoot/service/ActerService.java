package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.IRandom;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.ActerWithInitiative;
import com.Puj0.RPGSpringBoot.domain.acters.SortedActersList;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Enemy;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Troll;
import com.Puj0.RPGSpringBoot.domain.acters.hero.Hero;
import com.Puj0.RPGSpringBoot.domain.acters.hero.RoleClass;
import com.Puj0.RPGSpringBoot.repository.ActerRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ActerService implements IActerService {

    private static IRandom random;
    private static final Faker faker = new Faker();

    private final ActerRepository acterRepository;

    public ActerService(ActerRepository acterRepository, IRandom random) {
        log.info("New acterService bean");
        this.acterRepository = acterRepository;
        ActerService.random = random;
    }

    @Override
    public List<Acter> createActers(int range) {
        SortedActersList sortedActers = new SortedActersList();
        int numOfHeroes = random.nextInt(range, range * 2 + 1);
        int numOfEnemies = random.nextInt(numOfHeroes, numOfHeroes * 2 + 1);

        for(Acter acter : createHeroes(numOfHeroes)){
            sortedActers.addActer(new ActerWithInitiative(acter, random));
        }
        for(Acter acter : createEnemies(numOfEnemies)){
            sortedActers.addActer(new ActerWithInitiative(acter, random));
        }
        saveActers(sortedActers);
        return sortedActers.getActerList();
    }

    @Override
    public List<Acter> allActers() {
        return acterRepository.findAll();
    }

    private List<Hero> createHeroes(int numOfHeroes) {
        List<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < numOfHeroes; i++) {
            Hero newHero = new Hero(faker.lordOfTheRings().character(), RoleClass.values()[i % RoleClass.values().length],
                    random.nextInt(10, 26), random.nextInt(3, 11),
                    random.nextInt(2, 7), random.nextInt(1, 10));
            heroes.add(newHero);
        }
        return heroes;
    }

    private List<Enemy> createEnemies(int numOfEnemies) {
        List<Enemy> enemies = new ArrayList<>();
        boolean trollDoesNotExist = true;
        for (int i = 0; i < numOfEnemies; i++) {
            boolean isTroll = (trollDoesNotExist) || ((random.nextInt(1, 11) % 2) == 1);
            if (isTroll) {
                trollDoesNotExist = false;
                Troll newTroll = new Troll(faker.rickAndMorty().character(), random.nextInt(10, 26),
                        random.nextInt(3, 11), random.nextInt(2, 7),
                        random.nextInt(1, 10));
                enemies.add(newTroll);
            } else {
                Animal newAnimal = new Animal(faker.pokemon().name(), random.nextInt(5, 16),
                        random.nextInt(0, 6), random.nextInt(2, 5),
                        random.nextInt(1, 5));
                enemies.add(newAnimal);
            }
        }
        return enemies;
    }

    private void saveActers(SortedActersList acters){
        for (ActerWithInitiative acter : acters.getArray()) {
            acterRepository.save(acter.getActer());
        }
    }

}
