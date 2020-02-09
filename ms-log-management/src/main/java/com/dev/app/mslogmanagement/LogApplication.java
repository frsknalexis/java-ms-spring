package com.dev.app.mslogmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.dev.app.mslogmanagement.config.CassandraConfig;
import com.dev.app.mslogmanagement.config.WebConfig;

@EnableDiscoveryClient
@SpringBootApplication
@Import({WebConfig.class,  CassandraConfig.class})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class LogApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogApplication.class, args);
	}

}
