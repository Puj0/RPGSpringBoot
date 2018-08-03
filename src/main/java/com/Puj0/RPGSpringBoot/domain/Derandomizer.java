package com.Puj0.RPGSpringBoot.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class Derandomizer implements IRandom {

    @Override
    public Integer nextInt(int origin, int bound) {
        return origin;
    }
}
