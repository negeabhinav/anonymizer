package com.anonymize.demo.repository;

import com.anonymize.demo.model.Method;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MethodRepository extends JpaRepository<Method, Long> {
}
