package com.Puj0.RPGSpringBoot.domain.game;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MinimumHeroes {

    @NotNull
    @Min(1)
    private int minNumOfHeroes;
}
