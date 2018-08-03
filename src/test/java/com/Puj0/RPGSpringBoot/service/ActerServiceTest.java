package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.Derandomizer;
import com.Puj0.RPGSpringBoot.domain.IRandom;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.mapper.ActerMapper;
import com.Puj0.RPGSpringBoot.repository.ActerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActerServiceTest {

    private ActerService acterService;

    private static final String NAME = "Name";
    private static final int HEALTH_POINTS = 24;
    private static final int ATTACK = 2;
    private static final int DEFENCE = 1;
    private static final int INITIATIVE = 5;

    @Mock
    private ActerRepository acterRepository;

    @Mock
    private IRandom random;

    @Mock
    private ActerMapper acterMapper;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        acterService = new ActerService(acterRepository, random, acterMapper);
    }

    @Test
    void createActers() {

        when(random.nextInt(1,3)).thenReturn(1);

        acterService.createActers(1);

        verify(random, times(2)).nextInt(1,3);

        assertEquals(2, acterService.getAllActers().size());

    }

    @Test
    void allActers() {

        Acter acter = new Animal(NAME, HEALTH_POINTS, ATTACK, DEFENCE, INITIATIVE);
        ArrayList<Acter> acterData = new ArrayList<>();
        acterData.add(acter);

        when(acterService.getAllActers()).thenReturn(acterData);
        List<Acter> acters = acterService.getAllActers();

        assertEquals(acters.size(), 1);
        verify(acterRepository, times(1)).findAll();
    }
}