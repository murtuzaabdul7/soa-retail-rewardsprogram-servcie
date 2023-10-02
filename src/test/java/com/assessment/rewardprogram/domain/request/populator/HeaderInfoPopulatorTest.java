package com.assessment.rewardprogram.domain.request.populator;


import com.assessment.rewardprogram.domain.request.HeaderInfo;
import com.assessment.rewardprogram.utils.RetailRewardsProgramServiceConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

@Slf4j
@ExtendWith(MockitoExtension.class)
class HeaderInfoPopulatorTest {


    @InjectMocks
    private HeaderInfoPopulator headerInfoPopulator;

    @Mock
    private HeaderInfo headerInfo;

    @Test
    void populateHeaderTest() {
        HttpHeaders httpHeaders = getHeaders();

        headerInfoPopulator.populate(httpHeaders);

    }


    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(RetailRewardsProgramServiceConstants.ACTIVITY_ID, "12345");
        httpHeaders.set(RetailRewardsProgramServiceConstants.APPLICATION_ID, "RETAIL");
        httpHeaders.set(RetailRewardsProgramServiceConstants.INTERACTION_ID, "INTERACTIONID");
        httpHeaders.set(RetailRewardsProgramServiceConstants.WORKFLOW_ID, "WORKFLOWID");
        httpHeaders.set(RetailRewardsProgramServiceConstants.TIMESTAMP, "2022-10-22T08:21:37.916Z");
        return httpHeaders;
    }
}
