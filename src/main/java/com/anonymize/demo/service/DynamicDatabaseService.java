package com.anonymize.demo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class DynamicDatabaseService {
    private final JdbcTemplate jdbcTemplate;

    public DynamicDatabaseService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Method to execute a query
    public void executeQuery(String query) {
        jdbcTemplate.execute(query);
    }

    //update query
    public void updateQuery(String query) {
        jdbcTemplate.update(query);
    }

    // Example for fetching results
    public List<Object> queryForList(String query) {
        //return jdbcTemplate.queryForList(query);
        return jdbcTemplate.queryForList(query, Object.class);
    }

    //


}
