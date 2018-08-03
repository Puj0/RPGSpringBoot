package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.IRandom;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.ActerClass;
import com.Puj0.RPGSpringBoot.domain.acters.ActerWithInitiative;
import com.Puj0.RPGSpringBoot.domain.acters.SortedActersList;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Enemy;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Troll;
import com.Puj0.RPGSpringBoot.domain.acters.hero.Hero;
import com.Puj0.RPGSpringBoot.domain.acters.hero.RoleClass;
import com.Puj0.RPGSpringBoot.mapper.ActerMapper;
import com.Puj0.RPGSpringBoot.repository.ActerRepository;
import com.Puj0.RPGSpringBoot.view.ActerParameters;
import com.Puj0.RPGSpringBoot.view.ActerView;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ActerService implements IActerService {

    private static IRandom random;
    private static final Faker faker = new Faker();

    private final ActerRepository acterRepository;

    private final ActerMapper acterMapper;

    ActerService(ActerRepository acterRepository, IRandom random, ActerMapper acterMapper) {
        log.info("New acterService bean");
        this.acterRepository = acterRepository;
        this.acterMapper = acterMapper;
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
    public List<Acter> getAllActers() {
        return acterRepository.findAll();
    }

    @Override
    public ResponseEntity<ActerView> createActer(ActerParameters acterParameters) {

        ActerClass clazz = acterParameters.getClassName();
        Acter acter;
        switch (clazz){
            case HERO:
                acter = new Hero(acterParameters.getAttack(), acterParameters.getDefence(), acterParameters.getHealthPoints(), acterParameters.getInitiative(),
                         acterParameters.getName(), RoleClass.BARBARIAN);
                break;
            case TROLL:
                acter = new Troll(acterParameters.getName(), acterParameters.getAttack(),
                        acterParameters.getDefence(), acterParameters.getHealthPoints(), acterParameters.getInitiative());
                break;
            case ANIMAL:
                acter = new Animal(acterParameters.getName(), acterParameters.getAttack(),
                        acterParameters.getDefence(), acterParameters.getHealthPoints(), acterParameters.getInitiative());
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        acterRepository.save(acter);
        return new ResponseEntity<>(acterMapper.mapActerView(acter), HttpStatus.OK);
    }

    private List<Hero> createHeroes(int numOfHeroes) {
        List<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < numOfHeroes; i++) {
            Hero newHero = new Hero(random.nextInt(10, 26), random.nextInt(3, 11),
                    random.nextInt(2, 7), random.nextInt(1, 10),
                    faker.lordOfTheRings().character(), RoleClass.values()[i % RoleClass.values().length]);
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
