package com.anonymize.demo;

import com.anonymize.demo.model.Rule;
import com.anonymize.demo.repository.RuleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	private final RuleRepository ruleRepository;

	public DemoApplication(RuleRepository ruleRepository) {
		this.ruleRepository = ruleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// get all Rules from Anon_Rule table
	public List<Rule> getAllRules(){
		return ruleRepository.findAll();
	}

}
