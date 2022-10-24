package com.assessment.rewardprogram.domain.request.populator;


import com.assessment.rewardprogram.domain.request.HeaderInfo;
import com.assessment.rewardprogram.domain.request.PeriodRange;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class DomainObjectTest {

    @Test
    public void headerInfoTest() {
        HeaderInfo headerInfo = new HeaderInfo();
        headerInfo.setApplicationid("RETAIL");
        headerInfo.setActivityid("12345");
        headerInfo.setWorkdlowid("RewardPoints");
        headerInfo.setTimestamp("2022-10-23T20:11:20.874Z");
        headerInfo.setInteractionid("23456");

        log.info("headerInfo: " + headerInfo.toString());
        assertEquals("12345", headerInfo.getActivityid());
        assertEquals("RETAIL", headerInfo.getApplicationid());
        assertEquals("RewardPoints", headerInfo.getWorkdlowid());
        assertEquals("2022-10-23T20:11:20.874Z", headerInfo.getTimestamp());
        assertEquals("23456", headerInfo.getInteractionid());
    }


    @Test
    public void periodRangeTest() {
        PeriodRange periodRange = new PeriodRange();
        periodRange.setMonth("MAY");
        periodRange.setYear("2022");

        log.info("periodRange Object: " + periodRange.toString());
        assertEquals("MAY", periodRange.getMonth());
        assertEquals("2022", periodRange.getYear());
    }
}
