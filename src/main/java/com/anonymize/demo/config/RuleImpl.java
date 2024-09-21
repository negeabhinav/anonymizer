package com.anonymize.demo.config;

import com.anonymize.demo.model.Method;
import com.anonymize.demo.model.Rule;
import com.anonymize.demo.service.DynamicDatabaseService;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.datafaker.*;

import static javax.swing.UIManager.getInt;

public class RuleImpl {

    private final Faker faker = new Faker();

    public void applyRules(DataSource dataSource, HashMap<Rule, Method> ruleMethodMap) throws SQLException {
        for (Map.Entry<Rule, Method> entry : ruleMethodMap.entrySet()) {
            Rule rule = entry.getKey();
            Method method = entry.getValue();
            String sql = "SELECT " + rule.getPk_column_name() + " FROM " + rule.getTable_name();

            DynamicDatabaseService dynamicDatabaseService = new DynamicDatabaseService(dataSource);
            for (Object primaryKey : dynamicDatabaseService.queryForList(sql)) {
                String pk = primaryKey.toString();
                String updateSql = generateUpdateSql(rule, method, pk);
                System.out.println(updateSql);
                dynamicDatabaseService.updateQuery(updateSql);
            }
        }
    }

    private String generateUpdateSql(Rule rule, Method method, String pk) {
        String columnName = rule.getColumn_name();
        String pkColumnName = rule.getPk_column_name();
        String newValue = generateNewValue(method);

        return String.format("UPDATE %s SET %s = '%s' WHERE %s = '%s';",
                rule.getTable_name(), columnName, newValue, pkColumnName, pk);
    }

    private String generateNewValue(Method method) {
        switch (method.getAnon_method_name().toLowerCase()) {
            case "random_string":
                return faker.random().hex(10);
            case "random_name":
                return faker.name().fullName().replace("'", "");
            case "random_age":
                return String.valueOf(faker.number().numberBetween(10, 80));
            case "random_phone":
                return faker.phoneNumber().phoneNumber();
            case "random_address":
                return faker.address().fullAddress().replace("'", "");
            default:
                throw new IllegalArgumentException("Unknown anonymization method: " + method.getAnon_method_name());
        }
    }
}
