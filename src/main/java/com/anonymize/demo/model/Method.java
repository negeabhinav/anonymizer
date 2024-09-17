package com.anonymize.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity(name = "anon_method")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Method {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anon_method_id")
    private Long anon_method_id;
    private String anon_method_name;
    private String anon_method_desc;
    private Boolean active;
}
