package com.assessment.rewardprogram.integrationtests;


import com.assessment.rewardprogram.repository.SalesOrderDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@Disabled
@SpringBootTest
class DatabaseConnectivityITTest {

    @Autowired
    SalesOrderDetailsRepository salesOrderDetailsRepository;

    @Test
    void connectionTest() {
        log.info("SalesOrders by CustomerId: {}", salesOrderDetailsRepository.findAllByCustomerId("101"));
    }
}
