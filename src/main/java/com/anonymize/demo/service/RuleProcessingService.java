package com.anonymize.demo.service;

import com.anonymize.demo.config.DynamicDBBuilder;
import com.anonymize.demo.config.RuleImpl;
import com.anonymize.demo.model.Method;
import com.anonymize.demo.model.Rule;
import com.anonymize.demo.repository.RuleRepository;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Service
public class RuleProcessingService {

    DynamicDBBuilder dynamicDBBuilder = new DynamicDBBuilder();



    public void processAnonRules(String url, String username, String password, String driverClassName, RuleRepository ruleRepository){
        DataSource dataSource = dynamicDBBuilder.createDataSource(url,username,password,driverClassName);
        RuleImpl rule = new RuleImpl();
        try {
            rule.applyRules(dataSource, getRulesMap(ruleRepository));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<Rule, Method> getRulesMap(RuleRepository ruleRepository){

        List<Rule> allRules = ruleRepository.findAll();
        HashMap<Rule, Method> ruleMethod = new HashMap<>();
        for (Rule rule : allRules) {
            ruleMethod.put(rule,rule.getMethod());
            //System.out.println("rule.getMethod Data: " + rule.getMethod());
        }
        return ruleMethod;
    }
}
