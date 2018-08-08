package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.INameGenerator;
import com.Puj0.RPGSpringBoot.domain.acters.*;
import com.Puj0.RPGSpringBoot.domain.game.MinimumHeroes;
import com.Puj0.RPGSpringBoot.domain.random.IRandom;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Enemy;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Troll;
import com.Puj0.RPGSpringBoot.domain.acters.hero.Hero;
import com.Puj0.RPGSpringBoot.domain.acters.hero.RoleClass;
import com.Puj0.RPGSpringBoot.mapper.IActerMapper;
import com.Puj0.RPGSpringBoot.repository.IActerRepository;
import com.Puj0.RPGSpringBoot.view.ActerRequest;
import com.Puj0.RPGSpringBoot.view.ActerView;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ActerService implements IActerService {

    private static final int DOUBLE_RANGE = 2;
    private static final int HERO_ATTACK_MIN = 10;
    private static final int HERO_ATTACK_MAX = 26;
    private static final int HERO_DEFENCE_MIN = 3;
    private static final int HERO_DEFENCE_MAX = 11;
    private static final int HERO_HEALTH_MIN = 2;
    private static final int HERO_HEALTH_MAX = 7;
    private static final int HERO_INITIATIVE_MIN = 1;
    private static final int HERO_INITIATIVE_MAX = 10;
    private static final int TROLL_HEALTH_MIN = 10;
    private static final int TROLL_HEALTH_MAX = 26;
    private static final int TROLL_ATTACK_MIN = 3;
    private static final int TROLL_ATTACK_MAX = 11;
    private static final int TROLL_DEFENCE_MIN = 2;
    private static final int TROLL_DEFENCE_MAX = 7;
    private static final int TROLL_INITIATIVE_MIN = 1;
    private static final int TROLL_INITIATIVE_MAX = 10;
    private static final int ANIMAL_HEALTH_MIN = 5;
    private static final int ANIMAL_HEALTH_MAX = 16;
    private static final int ANIMAL_ATTACK_MIN = 0;
    private static final int ANIMAL_ATTACK_MAX = 6;
    private static final int ANIMAL_DEFENCE_MIN = 2;
    private static final int ANIMAL_DEFENCE_MAX = 5;
    private static final int ANIMAL_INITIATIVE_MIN = 1;
    private static final int ANIMAL_INITIATIVE_MAX = 5;

    private IRandom random;
    private INameGenerator nameGenerator;

    private final IActerRepository acterRepository;

    private final IActerMapper acterMapper;

    ActerService(IActerRepository acterRepository, IRandom random, IActerMapper acterMapper, INameGenerator nameGenerator) {
        this.acterRepository = acterRepository;
        this.acterMapper = acterMapper;
        this.random = random;
        this.nameGenerator = nameGenerator;
    }

    @Override
    public List<Acter> createActers(MinimumHeroes acterRange) {
        SortedActersList sortedActers = new SortedActersList();
        int numOfHeroes = random.nextInt(acterRange.getMinNumOfHeroes(), acterRange.getMinNumOfHeroes() * DOUBLE_RANGE + 1);
        int numOfEnemies = random.nextInt(numOfHeroes, numOfHeroes * DOUBLE_RANGE + 1);

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
    public ActerView createActer(ActerRequest acterRequest) {
        ActerFactory acterFactory = new ActerFactory();
        Acter acter = acterFactory.createActer(acterRequest);
        acterRepository.save(acter);
        return acterMapper.map(acter);
    }

    @Override
    public List<ActerView> findByAttackAndInitiative(int attack, Integer initiative) {

        List<Acter> acterList;
        if (initiative == null) {
            acterList = acterRepository.findByAttack(attack);
        } else {
            acterList = acterRepository.findByAttackAndInitiative(attack, initiative);
        }

        if (acterList.isEmpty()){
            return new ArrayList<>();
        } else {
            return acterList.stream()
                    .map(acterMapper::map)
                    .collect(Collectors.toList());
        }
    }

    private List<Hero> createHeroes(int numOfHeroes) {
        List<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < numOfHeroes; i++) {
            Hero newHero = new Hero(random.nextInt(HERO_ATTACK_MIN, HERO_ATTACK_MAX), random.nextInt(HERO_DEFENCE_MIN, HERO_DEFENCE_MAX),
                    random.nextInt(HERO_HEALTH_MIN, HERO_HEALTH_MAX), random.nextInt(HERO_INITIATIVE_MIN, HERO_INITIATIVE_MAX),
                    nameGenerator.getHeroName(), RoleClass.values()[i % RoleClass.values().length]);
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
                Troll newTroll = new Troll(nameGenerator.getTrollName(), random.nextInt(TROLL_HEALTH_MIN, TROLL_HEALTH_MAX),
                        random.nextInt(TROLL_ATTACK_MIN, TROLL_ATTACK_MAX), random.nextInt(TROLL_DEFENCE_MIN, TROLL_DEFENCE_MAX),
                        random.nextInt(TROLL_INITIATIVE_MIN, TROLL_INITIATIVE_MAX));
                enemies.add(newTroll);
            } else {
                Animal newAnimal = new Animal(nameGenerator.getAnimalName(), random.nextInt(ANIMAL_HEALTH_MIN, ANIMAL_HEALTH_MAX),
                        random.nextInt(ANIMAL_ATTACK_MIN, ANIMAL_ATTACK_MAX), random.nextInt(ANIMAL_DEFENCE_MIN, ANIMAL_DEFENCE_MAX),
                        random.nextInt(ANIMAL_INITIATIVE_MIN, ANIMAL_INITIATIVE_MAX));
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
