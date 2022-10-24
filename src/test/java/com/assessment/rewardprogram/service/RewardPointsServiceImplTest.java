package com.assessment.rewardprogram.service;


import com.assessment.rewardprogram.domain.request.PeriodRange;
import com.assessment.rewardprogram.domain.request.RewardPointsRequest;
import com.assessment.rewardprogram.domain.response.RewardPointsDetailsResponse;
import com.assessment.rewardprogram.entity.SalesOrderDetails;
import com.assessment.rewardprogram.repository.SalesOrderDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class RewardPointsServiceImplTest {

    @InjectMocks
    private RewardPointsServiceImpl rewardPointsService;

    @Mock
    private SalesOrderDetailsRepository salesOrderDetailsRepository;

    @ParameterizedTest
    @CsvFileSource(resources = "/calculateTestData.csv", numLinesToSkip = 1)
    public void calculateTest(double totalpurchaseamount, double expected) {
        double actual = rewardPointsService.calculateRewardPoints(totalpurchaseamount);
        log.info("totalpurchaseamount: {}, expectedValue: {}, actualValue: {}", totalpurchaseamount, expected, actual);
        assertEquals(expected, actual, 0.1);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/calculateRewardPointsData.csv", numLinesToSkip = 1)
    public void calculateRewardPointsTest(String salesordernumber, String customerid, double totalpurchaseamount,
                                          boolean rewardpointseligible, String startYear, String startMonth,
                                          String endYear, String endMonth, double expectedValue) {
        RewardPointsRequest request = new RewardPointsRequest();
        request.setCustomerIds(Collections.singletonList(customerid));
        request.setStartRange(new PeriodRange(startYear, startMonth));

        if (endYear.equals("null") || endMonth.equals("null")) {
            request.setEndRange(null);
        } else {
            request.setEndRange(new PeriodRange(endYear, endMonth));
        }

        SalesOrderDetails salesOrderDetails = new SalesOrderDetails();
        salesOrderDetails.setSalesOrderNumber(salesordernumber);
        salesOrderDetails.setCustomerId(customerid);
        salesOrderDetails.setTotalPurchaseAmount(totalpurchaseamount);
        salesOrderDetails.setRewardPointsEligible(rewardpointseligible);

        List<SalesOrderDetails> salesOrderDetailsList = new ArrayList<>();
        salesOrderDetailsList.add(salesOrderDetails);

        when(salesOrderDetailsRepository.findAllByCustomerIdByRange(
                any(), any(), any(), anyBoolean()
        )).thenReturn(salesOrderDetailsList);

        RewardPointsDetailsResponse actual = rewardPointsService.getRewardPoints(request);
        log.info("totalpurchaseamount: {}, expectedValue: {}, actualValue: {}", totalpurchaseamount,
                expectedValue, actual.getRewardPointsDetailsList().get(0).getTotalRewardPoints());

        assertEquals(customerid, actual.getRewardPointsDetailsList().get(0).getCustomerId());
        assertEquals(totalpurchaseamount, actual.getRewardPointsDetailsList().get(0).getTotalPurchaseOrderAmount());
        assertEquals(expectedValue, actual.getRewardPointsDetailsList().get(0).getTotalRewardPoints());

        verify(salesOrderDetailsRepository, times(1)).findAllByCustomerIdByRange(any(), any(), any(), anyBoolean());
    }

}
