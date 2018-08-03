package com.Puj0.RPGSpringBoot.view;

import com.Puj0.RPGSpringBoot.domain.acters.ActerClass;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ActerParameters {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private int healthPoints;
    @NotNull
    @Min(1)
    private int attack;
    @NotNull
    private int defence;
    @NotNull
    @Min(1)
    private int initiative;
    @NotNull
    private ActerClass className;

    public void setName(String name){
        this.name = name.trim();
    }
}
