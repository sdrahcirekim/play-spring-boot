package com.example.domain;

import java.util.List;

public class ExampleEntity {

    private List<ExampleValue> exampleValues;
    private String rootId;

    public ExampleEntity() { }

    public ExampleEntity(String rootId, List<ExampleValue> exampleValues) {
        this.rootId = rootId;
        this.exampleValues = exampleValues;
    }

    public List<ExampleValue> getExampleValues() {
        return exampleValues;
    }

    public String getRootId() {
        return rootId;
    }
}
