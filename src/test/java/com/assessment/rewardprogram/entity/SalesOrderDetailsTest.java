package com.assessment.rewardprogram.entity;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@ExtendWith(MockitoExtension.class)
class SalesOrderDetailsTest {

    @Test
    void testSalesOrderDetails() {
        SalesOrderDetails salesOrderDetails = new SalesOrderDetails();
        salesOrderDetails.setSalesOrderNumber("SALE101");
        salesOrderDetails.setTotalPurchaseAmount(100.0);
        log.info(salesOrderDetails.toString());
        assertNotNull(salesOrderDetails);
        assertEquals("SALE101", salesOrderDetails.getSalesOrderNumber());
        assertEquals(100.0, salesOrderDetails.getTotalPurchaseAmount());
    }

}
