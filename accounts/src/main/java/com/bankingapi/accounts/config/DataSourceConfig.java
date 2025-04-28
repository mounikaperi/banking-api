package com.bankingapi.accounts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.axis")
    public DataSource axisDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.icici")
    public DataSource iciciDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "hdfcDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hdfc")
    public DataSource hdfcDataSource() {
        return DataSourceBuilder.create().build();
    }
}
