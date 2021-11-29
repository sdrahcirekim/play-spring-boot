package com.example.service;

import com.example.domain.ExampleValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExampleEntityService {

    private final Map<String, List<ExampleValue>> entityValues;

    public ExampleEntityService(Map<String, List<ExampleValue>> entityValues) {
        this.entityValues = entityValues;
    }

    public Optional<List<ExampleValue>> getExampleEntityValues(String rootId) {
        return Optional.ofNullable(entityValues.get(rootId));
    }

    public void storeEntityValues(String rootId, List<ExampleValue> exampleValues) {
        if (!entityValues.containsKey(rootId)) {
            entityValues.put(rootId, new ArrayList<>());
        }
        entityValues.get(rootId).addAll(exampleValues);
    }
}
