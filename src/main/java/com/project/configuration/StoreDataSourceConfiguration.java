package com.project.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class StoreDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.store")
    public DataSourceProperties storeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean

    @ConfigurationProperties("spring.datasource.store.hikari")
    public DataSource storeDataSource() {
        return storeDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
