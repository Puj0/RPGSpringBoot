package com.Puj0.RPGSpringBoot.domain;

public class Derandomizer implements IRandom {

    @Override
    public Integer nextInt(int origin, int bound) {
        return origin;
    }

}
