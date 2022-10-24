package com.assessment.rewardprogram.integrationtests;


import com.assessment.rewardprogram.RetailRewardsProgramServiceApplication;
import com.assessment.rewardprogram.config.CassandraConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author Murtuza
 */

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ContextConfiguration(classes = RetailRewardsProgramServiceApplication.class)
public class RewardPointsDetailsApplicationITTest {
    @Autowired
    CassandraConfiguration cassandraConfiguration; //Do inject a bean to test if it is non-null

    @Test
    public void testSpringContextLoads() {
        assertNotNull(cassandraConfiguration);
    }
}
