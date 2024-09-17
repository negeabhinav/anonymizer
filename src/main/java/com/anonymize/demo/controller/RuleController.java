package com.anonymize.demo.controller;

import com.anonymize.demo.model.Rule;
import com.anonymize.demo.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RuleController {

    @Autowired
    private RuleRepository ruleRepository;

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
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Hello World");
    }

}
