package com.assessment.rewardprogram;

import com.assessment.rewardprogram.utils.RetailRewardsProgramServiceConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@Configuration
@ComponentScan(basePackages = {RetailRewardsProgramServiceConstants.REPO_BASE_PATH})
//@EnableCassandraRepositories(basePackages = RetailRewardsProgramServiceConstants.REPOSITORY_PATH)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RetailRewardsProgramServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailRewardsProgramServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
