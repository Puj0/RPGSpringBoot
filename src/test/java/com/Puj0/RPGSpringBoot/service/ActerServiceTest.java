package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.INameGenerator;
import com.Puj0.RPGSpringBoot.domain.ActerSearchRequest;
import com.Puj0.RPGSpringBoot.domain.game.MinimumHeroes;
import com.Puj0.RPGSpringBoot.domain.random.IRandom;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.enemy.Animal;
import com.Puj0.RPGSpringBoot.mapper.IActerMapper;
import com.Puj0.RPGSpringBoot.repository.IActerRepository;
import com.Puj0.RPGSpringBoot.view.ActerView;
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
    private IActerRepository acterRepository;

    @Mock
    private IRandom random;

    @Mock
    private IActerMapper acterMapper;

    @Mock
    private MinimumHeroes acterRange;

    @Mock
    private INameGenerator nameGenerator;

    @Mock
    private ActerSearchRequest acterSearchRequest;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        acterService = new ActerService(acterRepository, random, acterMapper, nameGenerator);
    }

    @Test
    void createActers() {

        when(random.nextInt(1,3)).thenReturn(1);
        when(acterRange.getMinNumOfHeroes()).thenReturn(1);
        when(acterSearchRequest.getAttack()).thenReturn(null);
        when(acterSearchRequest.getInitiative()).thenReturn(null);

        acterService.createActers(acterRange);

        verify(random, times(2)).nextInt(1,3);

        assertEquals(2, acterService.getActers(acterSearchRequest).size());

    }

    @Test
    void allActers() {

        Acter acter = new Animal(NAME, HEALTH_POINTS, ATTACK, DEFENCE, INITIATIVE);
        ArrayList<ActerView> acterData = new ArrayList<>();
        acterData.add(acterMapper.map(acter));

        when(acterSearchRequest.getAttack()).thenReturn(null);
        when(acterSearchRequest.getInitiative()).thenReturn(null);
        when(acterService.getActers(acterSearchRequest)).thenReturn(acterData);
        List<ActerView> acters = acterService.getActers(acterSearchRequest);

        assertEquals(acters.size(), 1);
        verify(acterRepository, times(1)).findAll();
    }
}