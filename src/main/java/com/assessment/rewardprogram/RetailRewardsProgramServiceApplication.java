package com.assessment.rewardprogram;

import com.assessment.rewardprogram.utils.RetailRewardsProgramServiceConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {RetailRewardsProgramServiceConstants.REPO_BASEPATH})
@EnableJpaRepositories(basePackages = RetailRewardsProgramServiceConstants.REPOSITORY_PATH)
public class RetailRewardsProgramServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RetailRewardsProgramServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
