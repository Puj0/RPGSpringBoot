package com.Puj0.RPGSpringBoot.domain.acters;

import com.Puj0.RPGSpringBoot.domain.Derandomizer;
import com.Puj0.RPGSpringBoot.domain.IRandom;
import com.Puj0.RPGSpringBoot.domain.ThreadRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


public class ActerWithInitiative {

    private Acter acter;
    private int initiative;

    private IRandom random = new ThreadRandom();

    public ActerWithInitiative(Acter acter, IRandom random){
        this.acter = acter;
        this.initiative = acter.getInitiative() + getRandomInitiative();
        this.random = random;
    }

    private int getRandomInitiative(){
        return random.nextInt(1, 21);
    }

    public Acter getActer(){
        return acter;
    }

    public int getInitiative() {
        return initiative;
    }

}
