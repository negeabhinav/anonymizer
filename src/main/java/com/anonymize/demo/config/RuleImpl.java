package com.anonymize.demo.config;

import com.anonymize.demo.model.Method;
import com.anonymize.demo.model.Rule;
import com.anonymize.demo.service.DynamicDatabaseService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.datafaker.*;

public class RuleImpl {


    public void applyRules(DataSource dataSource, HashMap<Rule, Method> ruleMethodMap){

        for (Map.Entry<Rule, Method> rule : ruleMethodMap.entrySet()){
            String sql = "select " + rule.getKey().getPk_column_name() + " from " + rule.getKey().getTable_name();

            DynamicDatabaseService dynamicDatabaseService = new DynamicDatabaseService(dataSource);
            //int cnt = ((Long)  dynamicDatabaseService.queryForList(sql).get(0).entrySet().iterator().next().getValue()).intValue();
            for (Object primary_key : dynamicDatabaseService.queryForList(sql)){
                String pk = primary_key.toString();

                if (rule.getValue().getAnon_method_name().equalsIgnoreCase("Random_String")) {
                    // faker.getRamdomString(cnt);
                    Faker faker = new Faker();
                    String updateSql = "update " + rule.getKey().getTable_name()
                            + " set " + rule.getKey().getColumn_name()
                            + " = '" + faker.random().hex(10) + "'" +
                            " where " + rule.getKey().getPk_column_name()
                            + " = " + "'" + pk + "'" ; // get value from the map
                    System.out.println(updateSql);
                }
            }



        }
    }


}
