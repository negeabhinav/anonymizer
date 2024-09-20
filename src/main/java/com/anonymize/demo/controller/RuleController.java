package com.anonymize.demo.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.anonymize.demo.config.RuleImpl;
import com.anonymize.demo.model.Method;
import com.anonymize.demo.model.Rule;
import com.anonymize.demo.repository.RuleRepository;
import com.anonymize.demo.service.RuleProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
public class RuleController {

    @Autowired
    private RuleRepository ruleRepository;
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driver;

    @PostMapping("/saveRules")
    public ResponseEntity<String> saveRules(@RequestBody List<Rule> ruleData){
        ruleData.forEach(rule -> {
            // Log each rule object to check the incoming data
            System.out.println("Received Rule: " + rule);
        });
        ruleRepository.saveAll(ruleData);
        return ResponseEntity.ok("Saved rules successfully");
    }

    @GetMapping("/helloWorld")
    public HashMap<Rule, Method> getMessage(){
        List<Rule> allRules = ruleRepository.findAll();
        HashMap<Rule, Method> ruleMethod = new HashMap<>();
        for (Rule rule : allRules) {
            ruleMethod.put(rule,rule.getMethod());
            //System.out.println("rule.getMethod Data: " + rule.getMethod());
        }
        return ruleMethod;
    }

//    @GetMapping("/getRuleDetails")
//    public ResponseEntity<String> getRuleDetails(){
//        RuleImpl rule = new RuleImpl();
//        rule.applyRules(getMessage());
//        return ResponseEntity.ok("Got All rule details");
//    }

    @GetMapping("/processRules")
    public ResponseEntity<String> processRules(){

        RuleProcessingService processRules = new RuleProcessingService();
        processRules.processAnonRules(url,username,password,driver, ruleRepository);
        return ResponseEntity.ok("Processed Rules");
    }

}
