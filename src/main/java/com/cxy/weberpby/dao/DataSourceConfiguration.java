package com.cxy.weberpby.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    // Connect to LYS_ERP
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.lyserp")
    public DataSource lyserpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate lyserpJdbcTemplate(@Qualifier("lyserpDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    // Connect to LIY_DD
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.liydd")
    public DataSource liyddDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate liyddJdbcTemplate(@Qualifier("liyddDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    // Connect to LBY_ERP
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.lbyerp")
    public DataSource lbyerpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate lbyerpJdbcTemplate(@Qualifier("lbyerpDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    // Connect to LBY_DD
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.lbydd")
    public DataSource lbyddDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate lbyddJdbcTemplate(@Qualifier("lbyddDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate myJdbcTemplate(@Qualifier("lbyddDataSource") DataSource myDataSource) {
        return new JdbcTemplate(myDataSource);
    }

    // Connct to WebERP
    // @Bean
    // @ConfigurationProperties(prefix = "spring.datasource.weberp")
    // public DataSource weberpDataSource() {
    //     return DataSourceBuilder.create().build();
    // }
    // @Bean
    // public NamedParameterJdbcTemplate weberpJdbcTemplate(@Qualifier("weberpDataSource") DataSource dataSource) {
    //     return new NamedParameterJdbcTemplate(dataSource);
    // }
}
