package com.example.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class ExampleValue {

    private Instant time;
    private BigDecimal value; // kW

    public ExampleValue() { }

    public ExampleValue(Instant time, BigDecimal value) {
        this.time = time;
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Instant getTime() {
        return time;
    }
}
