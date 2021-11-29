package com.example.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RootService {

    private final Map<String, String> rootIds;

    public RootService(Map<String, String> rootIds) {
        this.rootIds = rootIds;
    }

    public String getNameForRootId(String rootId) {
        return rootIds.get(rootId);
    }
}
