package com.example.generator;

import com.example.domain.ExampleValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ExampleValueGenerator {

    public List<ExampleValue> generate(int number) {
        List<ExampleValue> readings = new ArrayList<>();
        Instant now = Instant.now();

        Random readingRandomiser = new Random();
        for (int i = 0; i < number; i++) {
            double positiveRandomValue = Math.abs(readingRandomiser.nextGaussian());
            BigDecimal randomReading = BigDecimal.valueOf(positiveRandomValue).setScale(4, RoundingMode.CEILING);
            ExampleValue exampleValue = new ExampleValue(now.minusSeconds(i * 10), randomReading);
            readings.add(exampleValue);
        }

        readings.sort(Comparator.comparing(ExampleValue::getTime));
        return readings;
    }
}
