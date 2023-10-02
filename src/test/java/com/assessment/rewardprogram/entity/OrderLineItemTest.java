package com.assessment.rewardprogram.entity;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class OrderLineItemTest {

    @Test
    void testOrderLineItem() {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setOrderLineId("111");
        orderLineItem.setStatus("COMPLETED");
        orderLineItem.setRewardEligible(true);
        orderLineItem.setProductId("PID001");
        orderLineItem.setSoldAmount(170.0);

        log.info(orderLineItem.toString());
        assertNotNull(orderLineItem);
        assertEquals("111", orderLineItem.getOrderLineId());
        assertEquals("COMPLETED", orderLineItem.getStatus());
        assertEquals("PID001", orderLineItem.getProductId());
        assertEquals(170.0, orderLineItem.getSoldAmount());
        assertTrue(orderLineItem.isRewardEligible());
    }

}
