package com.Puj0.RPGSpringBoot.domain.random;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Profile("dev")
public class ThreadRandom implements IRandom {

    private ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    @Override
    public Integer nextInt(int origin, int bound) {
        return threadLocalRandom.nextInt(origin,bound);
    }
}
