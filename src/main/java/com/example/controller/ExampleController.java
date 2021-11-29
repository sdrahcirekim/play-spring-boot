package com.example.controller;

import com.example.domain.ExampleValue;
import com.example.domain.ExampleEntity;
import com.example.service.ExampleEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/values")
public class ExampleController {

    private final ExampleEntityService exampleEntityService;

    public ExampleController(ExampleEntityService exampleEntityService) {
        this.exampleEntityService = exampleEntityService;
    }

    @PostMapping("/store")
    public ResponseEntity storeExampleEntity(@RequestBody ExampleEntity exampleEntity) {
        if (!isExampleEntityValid(exampleEntity)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        exampleEntityService.storeEntityValues(exampleEntity.getRootId(), exampleEntity.getExampleValues());
        return ResponseEntity.ok().build();
    }

    private boolean isExampleEntityValid(ExampleEntity exampleEntity) {
        String rootId = exampleEntity.getRootId();
        List<ExampleValue> exampleValues = exampleEntity.getExampleValues();
        return rootId != null && !rootId.isEmpty()
                && exampleValues != null && !exampleValues.isEmpty();
    }

    @GetMapping("/read/{rootId}")
    public ResponseEntity readExampleValues(@PathVariable String rootId) {
        Optional<List<ExampleValue>> readings = exampleEntityService.getExampleEntityValues(rootId);
        return readings.isPresent()
                ? ResponseEntity.ok(readings.get())
                : ResponseEntity.notFound().build();
    }
}
