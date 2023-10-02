package com.assessment.rewardprogram.utils;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@ExtendWith(MockitoExtension.class)
class AlertLogTest {

    @Test
    void generateAlertTest() {
        AlertLog alertLog = AlertLog.generateAlert("204", "No Content",
                RetailRewardsProgramServiceConstants.SERVICE_NAME, "No Content Found for customerId 101",
                "headerInfo");
        log.info("alertLog: " + alertLog.toString());

        assertNotNull(alertLog);
        assertEquals("204", alertLog.getErrorCode());
        assertEquals("No Content", alertLog.getErrorDescription());
        assertEquals("No Content Found for customerId 101", alertLog.getSystemMessage());
        assertEquals("soa-retail-rewardprogram-servcie", alertLog.getServiceName());
        assertEquals("headerInfo", alertLog.getHeaderInfo());
    }
}