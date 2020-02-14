package com.dev.app.mscoursemanagement.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return adapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lcemb = new LocalContainerEntityManagerFactoryBean();
		lcemb.setJpaVendorAdapter(getJpaVendorAdapter());
		lcemb.setDataSource(dataSource());
		lcemb.setPersistenceUnitName("entityManagerFactory");
		lcemb.setPackagesToScan("com.dev.app.mscoursemanagement.entity.*");
		lcemb.setJpaProperties(hibernateProperties());
		return lcemb;
	}
	
	@Bean
	public PlatformTransactionManager transactionManagement() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}
	
	private final Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show-sql", env.getProperty("hibernate.show-sql"));
		properties.put("hibernate.format-sql", env.getProperty("hibernate.format-sql"));
		properties.put("hibernate.hbm2ddl-auto", env.getProperty("hibernate.hbm2ddl-auto"));
		properties.put("hibernate.max_fetch_depth", env.getProperty("hibernate.max_fetch_depth"));
		properties.put("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
		properties.put("hibernate.cache.use_minimal_puts", env.getProperty("hibernate.cache.use_minimal_puts"));
		properties.put("hibernate.connection_release_mode", env.getProperty("hibernate.connection_release_mode"));
		properties.put("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
		return properties;
	}
}
