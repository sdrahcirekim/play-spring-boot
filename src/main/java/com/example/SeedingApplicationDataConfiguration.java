package com.example;

import com.example.domain.ExampleValue;
import com.example.generator.ExampleValueGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class SeedingApplicationDataConfiguration {

    @Bean
    public Map<String, List<ExampleValue>> perEntityValues() {
        final Map<String, List<ExampleValue>> readings = new HashMap<>();
        final ExampleValueGenerator exampleValueGenerator = new ExampleValueGenerator();
        rootIds()
                .keySet()
                .forEach(smartMeterId -> readings.put(smartMeterId, exampleValueGenerator.generate(20)));
        return readings;
    }

    @Bean
    public Map<String, String> rootIds() {
        final Map<String, String> roots = new HashMap<>();
        roots.put("root-0", "example-0");
        roots.put("root-1", "example-1");
        roots.put("root-2", "example-2");
        roots.put("root-3", "example-3");
        roots.put("root-4", "example-4");
        return roots;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
