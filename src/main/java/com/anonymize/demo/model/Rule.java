package com.anonymize.demo.model;
import jakarta.persistence.*;

@Entity(name = "anon_rule")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rule_id;
    private String rule_name;
    private String table_name;
    private String column_name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_anon_method_id")
    private Method method;
}
