package com.Puj0.RPGSpringBoot.view;

import com.Puj0.RPGSpringBoot.domain.acters.ActerType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ActerRequest {

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
    private ActerType acterType;

    private int roleClass;

    public void setName(String name){
        this.name = name.trim();
    }
}
