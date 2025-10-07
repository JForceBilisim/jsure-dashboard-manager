package com.jforce.jsure.dashboard.manager.config;

import com.jforce.jsure.base.db.util.DataSourceManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@ConfigurationProperties(prefix = "jsure.database")
@Configuration
@Getter
@Setter
public class DatabaseConfiguration {

    private String username;
    private String password;
    private String url;
    private String driverClassName;
    private HashMap<String, String> additionalParameters;

    @Bean
    DataSource getDataSource() {
        return DataSourceManager.createDataSource(username, password, url, driverClassName, additionalParameters);
    }
}
