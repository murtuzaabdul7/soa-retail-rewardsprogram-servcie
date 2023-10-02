package com.assessment.rewardprogram.integrationtests;


import com.assessment.rewardprogram.RetailRewardsProgramServiceApplication;
import com.assessment.rewardprogram.config.CassandraConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Murtuza
 */

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ContextConfiguration(classes = RetailRewardsProgramServiceApplication.class)
class RewardPointsDetailsApplicationITTest {
    @Autowired
    CassandraConfiguration cassandraConfiguration; //Do inject a bean to test if it is non-null

    @Test
    void testSpringContextLoads() {
        assertNotNull(cassandraConfiguration);
    }
}
