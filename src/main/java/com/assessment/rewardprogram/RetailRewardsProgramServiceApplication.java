package com.assessment.rewardprogram;

import com.assessment.rewardprogram.utils.RetailRewardsProgramServiceConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages = {RetailRewardsProgramServiceConstants.REPO_BASE_PATH})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RetailRewardsProgramServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetailRewardsProgramServiceApplication.class, args);
    }
}
