package com.anonymize.demo.anonymization;

import net.datafaker.Faker;

public class RandomStringMethod implements AnonymizationMethod {
    private final Faker faker = new Faker();

    @Override
    public String generateValue() {
        return faker.random().hex(10);
    }
}