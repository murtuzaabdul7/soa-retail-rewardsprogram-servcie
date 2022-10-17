package com.assessment.rewardprogram.service;


import com.assessment.rewardprogram.domain.response.RewardPointsDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Slf4j
public class RewardPointsServiceImplTest {

    private RewardPointsServiceImpl rewardPointsService;

    @BeforeEach
    public void beforeEach() {
        rewardPointsService = new RewardPointsServiceImpl();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calculateTestData.csv", numLinesToSkip = 1)
    public void calculateTest(double totalpurchaseamount, double expected) {
        double actual = rewardPointsService.calculateRewardPoints(totalpurchaseamount);
        log.info("totalpurchaseamount: {}, expectedValue: {}, actualValue: {}", totalpurchaseamount, expected, actual);
        Assertions.assertEquals(expected, actual, 0.1);
    }


//    @ParameterizedTest
//    @CsvFileSource(resources = "/calculateRewardPointsData.csv", numLinesToSkip = 1)
//    public void calculateRewardPointsTest(double totalpurchaseamount, double expected) {
//        RewardPointsDetailsResponse actual = rewardPointsService.getRewardPoints(null);
//        log.info("totalpurchaseamount: {}, expectedValue: {}, actualValue: {}", totalpurchaseamount, expected, actual);
////        Assertions.assertEquals(expected, actual, 0.1);
//    }

}
