package com.Puj0.RPGSpringBoot.filter;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ActerSpecificationBuilder {

    private final List<FilterCriteria> parameters;

    public ActerSpecificationBuilder() {
        parameters = new ArrayList<>();
    }

    public ActerSpecificationBuilder with(String key, String operation, Object value) {
        parameters.add(new FilterCriteria(key, operation, value));
        return this;
    }

    public Specification<Acter> build() {
        if (parameters.isEmpty()) {
            return null;
        }

        Specification<Acter> result = new ActerSpecification(parameters.get(0));
        for (int i = 1; i < parameters.size(); i++) {
            result = Specification.where(result).and(new ActerSpecification(parameters.get(i)));
        }
        return result;
    }

    public final ActerSpecificationBuilder with(GameSpecification specification){
        parameters.add(specification.getCriteria());
        return this;
    }

    public final ActerSpecificationBuilder with(FilterCriteria criteria){
        parameters.add(criteria);
        return this;
    }
}
