package com.project.configuration;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MgmtDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.mgmt")
    public DataSourceProperties mgmtDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mgmtDataSource() {
        return mgmtDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
