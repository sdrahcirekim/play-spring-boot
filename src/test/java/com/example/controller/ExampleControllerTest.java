package com.example.controller;

import com.example.builders.ExampleEntityBuilder;
import com.example.domain.ExampleValue;
import com.example.domain.ExampleEntity;
import com.example.service.ExampleEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExampleControllerTest {

    private static final String ROOT_ID = "10101010";
    private ExampleController exampleController;
    private ExampleEntityService exampleEntityService;

    @BeforeEach
    public void setUp() {
        this.exampleEntityService = new ExampleEntityService(new HashMap<>());
        this.exampleController = new ExampleController(exampleEntityService);
    }

    @Test
    public void givenNoRootIdIsSuppliedWhenStoringShouldReturnErrorResponse() {
        ExampleEntity exampleEntity = new ExampleEntity(null, Collections.emptyList());
        assertThat(exampleController.storeExampleEntity(exampleEntity).getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void givenEmptyEntityReadingShouldReturnErrorResponse() {
        ExampleEntity exampleEntity = new ExampleEntity(ROOT_ID, Collections.emptyList());
        assertThat(exampleController.storeExampleEntity(exampleEntity).getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void givenNullValuesAreSuppliedWhenStoringShouldReturnErrorResponse() {
        ExampleEntity exampleEntity = new ExampleEntity(ROOT_ID, null);
        assertThat(exampleController.storeExampleEntity(exampleEntity).getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void givenMultipleBatchesOfValuesShouldStore() {
        ExampleEntity exampleEntity = new ExampleEntityBuilder().setRootId(ROOT_ID)
                .generateExampleValues()
                .build();

        ExampleEntity otherExampleEntity = new ExampleEntityBuilder().setRootId(ROOT_ID)
                .generateExampleValues()
                .build();

        exampleController.storeExampleEntity(exampleEntity);
        exampleController.storeExampleEntity(otherExampleEntity);

        List<ExampleValue> expectedExampleValues = new ArrayList<>();
        expectedExampleValues.addAll(exampleEntity.getExampleValues());
        expectedExampleValues.addAll(otherExampleEntity.getExampleValues());

        assertThat(exampleEntityService.getExampleEntityValues(ROOT_ID).get()).isEqualTo(
            expectedExampleValues);
    }

    @Test
    public void givenValuesAssociatedWithTheRootShouldStoreAssociatedWithRoot() {
        ExampleEntity exampleEntity = new ExampleEntityBuilder().setRootId(ROOT_ID)
                .generateExampleValues()
                .build();

        ExampleEntity otherExampleEntity = new ExampleEntityBuilder().setRootId("00001")
                .generateExampleValues()
                .build();

        exampleController.storeExampleEntity(exampleEntity);
        exampleController.storeExampleEntity(otherExampleEntity);

        assertThat(exampleEntityService.getExampleEntityValues(ROOT_ID).get()).isEqualTo(exampleEntity.getExampleValues());
    }

    @Test
    public void givenMeterIdThatIsNotRecognisedShouldReturnNotFound() {
        assertThat(exampleController.readExampleValues(ROOT_ID).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
