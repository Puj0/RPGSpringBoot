package com.Puj0.RPGSpringBoot.filter;

import com.Puj0.RPGSpringBoot.domain.game.Game;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GameSpecificationBuilder {

    private final List<FilterCriteria> parameters;

    public GameSpecificationBuilder() {
        parameters = new ArrayList<>();
    }

    public GameSpecificationBuilder with(String key, String operation, Object value) {
        parameters.add(new FilterCriteria(key, operation, value));
        return this;
    }

    public Specification<Game> build() {
        if (parameters.isEmpty()) {
            return null;
        }

        Specification<Game> result = new GameSpecification(parameters.get(0));
        for (int i = 1; i < parameters.size(); i++) {
            result = Specification.where(result).and(new GameSpecification(parameters.get(i)));
        }
        return result;
    }

    public final GameSpecificationBuilder with(GameSpecification specification){
        parameters.add(specification.getCriteria());
        return this;
    }

    public final GameSpecificationBuilder with(FilterCriteria criteria){
        parameters.add(criteria);
        return this;
    }
}
