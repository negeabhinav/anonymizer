package com.anonymize.demo.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@ToString
@Entity(name = "anon_method")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
