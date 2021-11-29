package com.example.builders;

import com.example.domain.ExampleValue;
import com.example.domain.ExampleEntity;
import com.example.generator.ExampleValueGenerator;

import java.util.ArrayList;
import java.util.List;

public class ExampleEntityBuilder {

    private static final String DEFAULT_ROOT_ID = "id";

    private String rootId = DEFAULT_ROOT_ID;
    private List<ExampleValue> exampleValues = new ArrayList<>();

    public ExampleEntityBuilder setRootId(String rootId) {
        this.rootId = rootId;
        return this;
    }

    public ExampleEntityBuilder generateExampleValues() {
        return generateExampleValues(5);
    }

    public ExampleEntityBuilder generateExampleValues(int number) {
        ExampleValueGenerator valueBuilder = new ExampleValueGenerator();
        this.exampleValues = valueBuilder.generate(number);
        return this;
    }

    public ExampleEntity build() {
        return new ExampleEntity(rootId, exampleValues);
    }
}
