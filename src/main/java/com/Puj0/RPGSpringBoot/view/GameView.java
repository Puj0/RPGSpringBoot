package com.Puj0.RPGSpringBoot.view;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.acters.SortedActersList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class GameView {

    private Long id;

    private String result;

    private int totalRounds;

    private SortedActersList acters;

    private ArrayList<Acter> removedActers = new ArrayList<>();

    private int currentRound;
}
