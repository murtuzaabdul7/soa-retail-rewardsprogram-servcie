package com.assessment.rewardprogram.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CassandraConfigurationTest {

    @InjectMocks
    private CassandraConfiguration cassandraConfiguration;

    @BeforeEach
    void beforeEachTest() {
        ReflectionTestUtils.setField(cassandraConfiguration, "port", 9042);
        ReflectionTestUtils.setField(cassandraConfiguration, "keySpace", "localKeyspaceName");
        ReflectionTestUtils.setField(cassandraConfiguration, "contactPoints", "127.0.0.1");
        ReflectionTestUtils.setField(cassandraConfiguration, "localDataCenter", "datacenter1");
    }


    @Test
    void testCassandraConfiguration() {
        log.info("port: " + cassandraConfiguration.getPort());
        log.info("contactPoints: " + cassandraConfiguration.getContactPoints());
        log.info("keyspaceName: " + cassandraConfiguration.getKeyspaceName());
        log.info("localDataCenter: " + cassandraConfiguration.getLocalDataCenter());
    }

}
