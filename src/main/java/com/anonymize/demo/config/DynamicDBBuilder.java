package com.anonymize.demo.config;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DynamicDBBuilder {
    private String url;
    private String username;
    private String password;
    private String driver;


    public DataSource createDataSource(String url, String username, String password, String driverClassName) {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }
}
