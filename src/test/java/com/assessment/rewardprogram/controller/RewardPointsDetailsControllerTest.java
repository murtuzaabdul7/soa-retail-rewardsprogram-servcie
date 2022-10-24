package com.assessment.rewardprogram.controller;


import com.assessment.rewardprogram.domain.request.RewardPointsRequest;
import com.assessment.rewardprogram.domain.request.populator.HeaderInfoPopulator;
import com.assessment.rewardprogram.domain.response.RewardPointsDetails;
import com.assessment.rewardprogram.domain.response.RewardPointsDetailsResponse;
import com.assessment.rewardprogram.service.RewardPointsServiceImpl;
import com.assessment.rewardprogram.utils.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class RewardPointsDetailsControllerTest {

    @InjectMocks
    private RewardPointsDetailsController rewardPointsDetailsController;

    @Mock
    private RewardPointsServiceImpl rewardPointsService;

    @Mock
    private HeaderInfoPopulator headerInfoPopulator;

    @Mock
    private Validator validator;

    @Mock
    private HttpHeaders headers;

    @ParameterizedTest
    @CsvFileSource(resources = "/getRewardPointsControllerTest.csv", numLinesToSkip = 1)
    public void getRewardPointsDetailsTest(String customerid, String startDate, String endDate,
                                           double totalpurchaseamount, int totalPurchaseOrders,
                                           double rewardPoints) {
        RewardPointsRequest request = new RewardPointsRequest();
        request.setCustomerIds(Collections.singletonList(customerid));

        RewardPointsDetailsResponse pointsDetailsResponse = new RewardPointsDetailsResponse();

        RewardPointsDetails rewardPointsDetails = new RewardPointsDetails(customerid, startDate, endDate,
                rewardPoints, totalPurchaseOrders, totalpurchaseamount);
        log.info("rewardPointsDetails: " + rewardPointsDetails.toString());
        pointsDetailsResponse.setRewardPointsDetailsList(Collections.singletonList(rewardPointsDetails));

        doNothing().when(headerInfoPopulator).populate(headers);
        doNothing().when(validator).validateRequestObject(request);

        when(rewardPointsService.getRewardPoints(request)).thenReturn(pointsDetailsResponse);

        RewardPointsDetailsResponse actualResponse = rewardPointsDetailsController.getRewardPointsDetails(headers, request);
        assertEquals(pointsDetailsResponse, actualResponse);
        verify(headerInfoPopulator, times(1)).populate(headers);
        verify(validator, times(1)).validateRequestObject(request);
        verify(rewardPointsService, times(1)).getRewardPoints(request);
    }
}