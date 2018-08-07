package com.Puj0.RPGSpringBoot.repository;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class IActerRepositoryIT {

    @Autowired
    IActerRepository acterRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAll() {

        List<Acter> acters = acterRepository.findAll();

        assertEquals(0, acters.size());
    }
}