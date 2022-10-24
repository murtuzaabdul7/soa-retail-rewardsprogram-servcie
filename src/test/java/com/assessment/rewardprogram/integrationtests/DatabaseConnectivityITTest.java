package com.assessment.rewardprogram.integrationtests;


import com.assessment.rewardprogram.repository.SalesOrderDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@Ignore
@SpringBootTest
@RunWith(SpringRunner.class)
public class DatabaseConnectivityITTest {

    @Autowired
    SalesOrderDetailsRepository salesOrderDetailsRepository;

    @Test
    public void connectionTest() {
        log.info("SalesOrders by CustomerId: {}", salesOrderDetailsRepository.findAllByCustomerId("101"));
    }
}
