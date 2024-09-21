package com.anonymize.demo.config;

import com.anonymize.demo.model.Method;
import com.anonymize.demo.model.Rule;
import com.anonymize.demo.service.DynamicDatabaseService;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.datafaker.*;

import static javax.swing.UIManager.getInt;

public class RuleImpl {


    public void applyRules(DataSource dataSource, HashMap<Rule, Method> ruleMethodMap) throws SQLException {

        for (Map.Entry<Rule, Method> rule : ruleMethodMap.entrySet()){
            String sql = "select " + rule.getKey().getPk_column_name() + " from " + rule.getKey().getTable_name();

            DynamicDatabaseService dynamicDatabaseService = new DynamicDatabaseService(dataSource);
            //int cnt = ((Long)  dynamicDatabaseService.queryForList(sql).get(0).entrySet().iterator().next().getValue()).intValue();
            for (Object primary_key : dynamicDatabaseService.queryForList(sql)){
                String pk = primary_key.toString();
                //int col_siz=dataSource.getConnection().getMetaData().getColumns(null,null,rule.getKey().getTable_name(),rule.getKey().getColumn_name()).getInt("COLUMN_SIZE");
                //System.out.println("col_siz-->"+col_siz);
            //getConnection().getMetaData().getColumns(null,null,rule.getKey().getTable_name(),rule.getKey().getColumn_name())

                Faker faker = new Faker();
                if (rule.getValue().getAnon_method_name().equalsIgnoreCase("Random_String")) {
                    // faker.getRamdomString(cnt);
                    String updateSql = "update " + rule.getKey().getTable_name()
                            + " set " + rule.getKey().getColumn_name()
                            + " = '" + faker.random().hex(10) + "'" +
                            " where " + rule.getKey().getPk_column_name()
                            + " = " + "'" + pk + "';" ; // get value from the map
                    System.out.println(updateSql);
                    dynamicDatabaseService.updateQuery(updateSql);
                }else if(rule.getValue().getAnon_method_name().equalsIgnoreCase("Random_Name")){

                    String updateSql = "update " + rule.getKey().getTable_name()
                            + " set " + rule.getKey().getColumn_name()
                            + " = '" + faker.name().fullName().replace("'","")+ "'" +
                            " where " + rule.getKey().getPk_column_name()
                            + " = " + "'" + pk + "';" ; // get value from the map
                    System.out.println(updateSql);
                    dynamicDatabaseService.updateQuery(updateSql);
                }else if(rule.getValue().getAnon_method_name().equalsIgnoreCase("Random_Age")){

                    String updateSql = "update " + rule.getKey().getTable_name()
                            + " set " + rule.getKey().getColumn_name()
                            + " = '" + faker.number().numberBetween(10,80)+ "'" +
                            " where " + rule.getKey().getPk_column_name()
                            + " = " + "'" + pk + "';" ; // get value from the map
                    System.out.println(updateSql);
                    dynamicDatabaseService.updateQuery(updateSql);
                }else if(rule.getValue().getAnon_method_name().equalsIgnoreCase("Random_Phone")){

                    String updateSql = "update " + rule.getKey().getTable_name()
                            + " set " + rule.getKey().getColumn_name()
                            + " = '" + faker.phoneNumber().phoneNumber()+ "'" +
                            " where " + rule.getKey().getPk_column_name()
                            + " = " + "'" + pk + "';" ; // get value from the map
                    System.out.println(updateSql);
                    dynamicDatabaseService.updateQuery(updateSql);
                }else if(rule.getValue().getAnon_method_name().equalsIgnoreCase("Random_Address")){

                    String updateSql = "update " + rule.getKey().getTable_name()
                            + " set " + rule.getKey().getColumn_name()
                            + " = '" + faker.address().fullAddress()+ "'" +
                            " where " + rule.getKey().getPk_column_name()
                            + " = " + "'" + pk + "';" ; // get value from the map
                    System.out.println(updateSql);
                    dynamicDatabaseService.updateQuery(updateSql);
                }
            }



        }
    }


}
