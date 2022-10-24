package com.assessment.rewardprogram.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class CassandraConfigurationTest {

    @InjectMocks
    private CassandraConfiguration cassandraConfiguration;

    @Before
    public void beforeEachTest() {
        ReflectionTestUtils.setField(cassandraConfiguration, "port", 9042);
        ReflectionTestUtils.setField(cassandraConfiguration, "keySpace", "localKeyspaceName");
        ReflectionTestUtils.setField(cassandraConfiguration, "contactPoints", "127.0.0.1");
        ReflectionTestUtils.setField(cassandraConfiguration, "localDataCenter", "datacenter1");
    }


    @Test
    public void testCassandraConfiguration() {
        log.info("port: " + cassandraConfiguration.getPort());
        log.info("contactPoints: " + cassandraConfiguration.getContactPoints());
        log.info("keyspaceName: " + cassandraConfiguration.getKeyspaceName());
        log.info("localDataCenter: " + cassandraConfiguration.getLocalDataCenter());
    }

}
