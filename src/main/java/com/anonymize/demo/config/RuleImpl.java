package com.anonymize.demo.config;

import com.anonymize.demo.model.Method;
import com.anonymize.demo.model.Rule;
import com.anonymize.demo.service.DynamicDatabaseService;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
//import java.util.function.Supplier;


import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.datafaker.*;

import static javax.swing.UIManager.getInt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anonymize.demo.anonymization.AnonymizationMethod;
import com.anonymize.demo.anonymization.AnonymizationMethodFactory;


public class RuleImpl {

    public void applyRules(DataSource dataSource, HashMap<Rule, Method> ruleMethodMap) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            for (Map.Entry<Rule, Method> entry : ruleMethodMap.entrySet()) {
                Rule rule = entry.getKey();
                Method method = entry.getValue();
                String sql = "SELECT " + rule.getPk_column_name() + " FROM " + rule.getTable_name();

                DynamicDatabaseService dynamicDatabaseService = new DynamicDatabaseService(dataSource);
                List<Object> primaryKeys = dynamicDatabaseService.queryForList(sql);

                for (Object primaryKey : primaryKeys) {
                    String pk = primaryKey.toString();
                    String updateSql = generateUpdateSql(rule, method, pk);
                    System.out.println(updateSql);  // For logging, can be removed in production
                    statement.addBatch(updateSql);
                }
            }
            statement.executeBatch();  // Execute all updates in bulk
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
        AnonymizationMethod anonymizationMethod = AnonymizationMethodFactory.getMethod(method.getAnon_method_name());
        return anonymizationMethod.generateValue();
    }
}
