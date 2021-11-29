package com.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExampleEntityServiceTest {

    private ExampleEntityService exampleEntityService;

    @BeforeEach
    public void setUp() {
        exampleEntityService = new ExampleEntityService(new HashMap<>());
    }

    @Test
    public void givenRootIdThatDoesNotExistShouldReturnNull() {
        assertThat(exampleEntityService.getExampleEntityValues("unknown-id")).isEqualTo(Optional.empty());
    }

    @Test
    public void givenEntityThatExistsShouldReturnMeterReadings() {
        exampleEntityService.storeEntityValues("random-id", new ArrayList<>());
        assertThat(exampleEntityService.getExampleEntityValues("random-id")).isEqualTo(Optional.of(new ArrayList<>()));
    }
}
