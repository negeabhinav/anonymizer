package com.anonymize.demo.anonymization;

import net.datafaker.Faker;

public class RandomAddressMethod implements AnonymizationMethod {
    private final Faker faker = new Faker();

    @Override
    public String generateValue() {
        return faker.address().fullAddress().replace("'", "");
    }
}