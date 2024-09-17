package com.anonymize.demo.controller;

import com.anonymize.demo.model.Rule;
import com.anonymize.demo.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RuleController {

    @Autowired
    private RuleRepository ruleRepository;

    @PostMapping(name = "/saveRules")
    public ResponseEntity<String> saveRules(@RequestBody List<Rule> ruleData){
        ruleRepository.saveAll(ruleData);
        return ResponseEntity.ok("Saved rules successfully");
    }

}
