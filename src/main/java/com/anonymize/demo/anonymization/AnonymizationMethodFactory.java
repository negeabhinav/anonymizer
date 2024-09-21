package com.anonymize.demo.anonymization;

import java.util.HashMap;
import java.util.Map;

public class AnonymizationMethodFactory {
    private static final Map<String, AnonymizationMethod> methodMap = new HashMap<>();

    static {
        methodMap.put("random_string", new RandomStringMethod());
        methodMap.put("random_name", new RandomNameMethod());
        methodMap.put("random_age", new RandomAgeMethod());
        methodMap.put("random_phone", new RandomPhoneMethod());
        methodMap.put("random_address", new RandomAddressMethod());
    }

    public static AnonymizationMethod getMethod(String methodName) {
        AnonymizationMethod method = methodMap.get(methodName.toLowerCase());
        if (method == null) {
            throw new IllegalArgumentException("Unknown anonymization method: " + methodName);
        }
        return method;
    }
}
