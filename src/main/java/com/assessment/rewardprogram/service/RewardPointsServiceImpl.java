package com.assessment.rewardprogram.service;

import com.assessment.rewardprogram.domain.request.PeriodRange;
import com.assessment.rewardprogram.domain.request.RewardPointsRequest;
import com.assessment.rewardprogram.domain.response.RewardPointsDetails;
import com.assessment.rewardprogram.domain.response.RewardPointsDetailsResponse;
import com.assessment.rewardprogram.entity.SalesOrderDetails;
import com.assessment.rewardprogram.repository.SalesOrderDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RewardPointsServiceImpl implements RewardPointsService {

    @Autowired
    private SalesOrderDetailsRepository salesOrderDetailsRepository;

    private String startDate;
    private Instant startDateInstant;
    private String endDate;
    private Instant endDateInstant;

    public RewardPointsServiceImpl() {
    }


    /**
     * The getRewardPoints function gets RewardsPoints for each Customer mentioned in rewardPointsRequest
     * and compiles them into a single response object.
     *
     * @param rewardPointsRequest
     * @return RewardPointsDetailsResponse
     */
    @Override
    public RewardPointsDetailsResponse getRewardPoints(RewardPointsRequest rewardPointsRequest) {
        setStartAndEndDate(rewardPointsRequest);

        RewardPointsDetailsResponse pointsDetailsResponse = new RewardPointsDetailsResponse();
        List<RewardPointsDetails> rewardPointsDetailsList = new ArrayList<>();

        for (String customerId : rewardPointsRequest.getCustomerIdList()) {
            RewardPointsDetails rewardPointsDetails = getRewardsPointsForEachCustomer(customerId);
            rewardPointsDetailsList.add(rewardPointsDetails);
        }

        pointsDetailsResponse.setRewardPointsDetailsList(rewardPointsDetailsList);
        return pointsDetailsResponse;
    }

    /**
     * The getRewardsPointsForEachCustomer fetches the eligible salesOrder for a customer in a given range
     * from database and calculates the total reward points.
     *
     * @param customerId
     * @return RewardPointsDetails
     */
    private RewardPointsDetails getRewardsPointsForEachCustomer(String customerId) {

        List<SalesOrderDetails> salesOrderDetailsList = salesOrderDetailsRepository
                .findAllByCustomerIdByRange(customerId,
                        startDateInstant, endDateInstant, true);

        log.info("orders of customerId {}: {}", customerId, salesOrderDetailsList.toString());
        double totalRewardPoints = 0.0;
        double totalEligiblePurchasedAmount = 0.0;
        for (SalesOrderDetails salesOrderDetails : salesOrderDetailsList) {
            if (salesOrderDetails.isRewardPointsEligible()) {
                double rewardPoint = calculateRewardPoints(salesOrderDetails.getTotalPurchaseAmount());
                totalRewardPoints += rewardPoint;
                totalEligiblePurchasedAmount += salesOrderDetails.getTotalPurchaseAmount();
            }
        }
        RewardPointsDetails rewardPointsDetails = new RewardPointsDetails();
        rewardPointsDetails.setCustomerId(customerId);
        rewardPointsDetails.setStartDate(startDate);
        rewardPointsDetails.setEndDate(endDate);
        rewardPointsDetails.setTotalRewardPoints(totalRewardPoints);
        rewardPointsDetails.setTotalPurchaseOrderAmount(totalEligiblePurchasedAmount);
        rewardPointsDetails.setTotalPurchaseOrders(salesOrderDetailsList.size());
        return rewardPointsDetails;
    }

    /**
     * The calculateRewardPoints function takes totalPurchaseAmount and
     * returns the total reward points based on the criteria
     * "A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point
     * for every dollar spent over $50 in each transaction"
     *
     * @param totalPurchaseAmount
     * @return rewardPoints
     */
    public double calculateRewardPoints(Double totalPurchaseAmount) {
        double rewardPoints = 0.0;
        if (totalPurchaseAmount >= 50.0 && totalPurchaseAmount <= 100.0) {
            rewardPoints += (totalPurchaseAmount - 50.0) * 1.0;
            return Math.floor(rewardPoints * 100) / 100;

        } else if (totalPurchaseAmount > 100.0) {
            rewardPoints += (totalPurchaseAmount - 100.0) * 2.0;
            return Math.floor(rewardPoints * 100) / 100 + 50.0;

        } else {
            return 0.0;
        }
    }


    /**
     * The getStartAndEndDate function identifies the start and end DateTime for which the
     * reward points need to be fetched from the database.
     * ex:
     * start - 2022-09-0101T00:00:01Z
     * end - 2022-10-31T23:59:59Z
     */
    private void setStartAndEndDate(RewardPointsRequest rewardPointsRequest) {
        PeriodRange range1 = rewardPointsRequest.getStartRange();
        PeriodRange range2 = rewardPointsRequest.getEndRange();

        // if range2 is null. then the request is for a given specific month. ex: 2022-05-01 to 2022-05-31.
        // else considers start and end date of given months. ex: 2022-05-01 to 2022-07-31.
        if (null == range2) {
            YearMonth yearMonth = YearMonth.of(Integer.parseInt(range1.getYear()), Month.valueOf(range1.getMonth().toUpperCase()));     // 2015-01. January of 2015.
            startDate = yearMonth.atDay(1).atTime(0, 0, 0, 1)
                    .atOffset(ZoneOffset.UTC).toString();     // ex: 2022-10-01

            endDate = yearMonth.atEndOfMonth().atTime(23, 59, 59, 999999999)
                    .atZone(ZoneOffset.UTC).toString(); // ex: 2022-10-31

            startDateInstant = Instant.parse(startDate);
            endDateInstant = Instant.parse(endDate);

            log.info("startDate: " + startDate);
            log.info("endDate: " + endDate);
        } else {
            YearMonth yearMonthStart = YearMonth.of(Integer.parseInt(range1.getYear()),
                    Month.valueOf(range1.getMonth().toUpperCase()));     // 2022-09-01.
            startDate = yearMonthStart.atDay(1).atTime(0, 0, 0, 1)
                    .atOffset(ZoneOffset.UTC).toString();     // ex: start - 2022-09-0101T00:00:01Z

            YearMonth yearMonthEnd = YearMonth.of(Integer.parseInt(range2.getYear()),
                    Month.valueOf(range2.getMonth().toUpperCase()));     // 2022-10-31
            endDate = yearMonthEnd.atEndOfMonth().atTime(23, 59, 59, 999999999)
                    .atZone(ZoneOffset.UTC).toString();  // ex: end - 2022-10-31T23:59:59Z

            startDateInstant = Instant.parse(startDate);
            endDateInstant = Instant.parse(endDate);

            log.info("startDate: " + startDate);

            log.info("endDate: " + endDate);
        }

    }
}
