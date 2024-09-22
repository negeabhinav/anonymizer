package com.anonymize.demo.anonymization;

import net.datafaker.Faker;

public class RandomAgeMethod implements AnonymizationMethod {
    private final Faker faker = new Faker();

    @Override
    public String generateValue() {
        return String.valueOf(faker.number().numberBetween(10, 80));
    }
}