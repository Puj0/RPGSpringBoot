package com.Puj0.RPGSpringBoot.view;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class GameRequest {

    @NotNull
    @Min(0)
    private Integer rounds;
    private List<Long> IDs;
}
