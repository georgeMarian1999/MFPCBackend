package com.project.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "storeEntityManagerFactory",
        transactionManagerRef = "storeTransactionManager",
        basePackages = "com.project.repo.store"
)
public class StoreJPAConfiguration {
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean storeEntityManagerFactory(
            @Qualifier("storeDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {

        LocalContainerEntityManagerFactoryBean em =
                builder.dataSource(dataSource).packages("com.project.model.store").build();
        em.setPersistenceUnitName("store");
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager storeTransactionManager(
            @Qualifier("storeEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
    }
}
