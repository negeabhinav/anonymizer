package com.anonymize.demo.anonymization;

import net.datafaker.Faker;

public class RandomNameMethod implements AnonymizationMethod {
    private final Faker faker = new Faker();

    @Override
    public String generateValue() {
        return faker.name().fullName().replace("'", "");
    }
}