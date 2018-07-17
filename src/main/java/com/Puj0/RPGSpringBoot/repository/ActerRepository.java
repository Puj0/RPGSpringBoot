package com.Puj0.RPGSpringBoot.repository;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.SortedActersList;
import org.springframework.data.repository.CrudRepository;

public interface ActerRepository extends CrudRepository<Acter, Long> {

//    void createCharacters(int numberOfHeroes, int range);
//    SortedActersList getSortedActers();
//    void addActersToDatabase(ActerWithInitiative[] acters);

    Iterable<Acter> findAll();

}
