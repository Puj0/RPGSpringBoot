package com.Puj0.RPGSpringBoot.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterCriteria {

    private String key;
    private String operation;
    private Object value;
}
