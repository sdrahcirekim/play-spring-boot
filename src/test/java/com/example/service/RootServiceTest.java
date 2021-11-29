package com.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RootServiceTest {

    private static final String NAME = "name";
    private static final String ROOT_ID = "root-id";

    private RootService rootService;

    @BeforeEach
    public void setUp() {
        Map<String, String> roots = new HashMap<>();
        roots.put(ROOT_ID, NAME);

        rootService = new RootService(roots);
    }

    @Test
    public void givenTheRootIdReturnsTheName() throws Exception {
        assertThat(rootService.getNameForRootId(ROOT_ID)).isEqualTo(NAME);
    }
}
