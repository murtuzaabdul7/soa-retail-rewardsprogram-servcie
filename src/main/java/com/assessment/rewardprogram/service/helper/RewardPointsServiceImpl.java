package com.assessment.rewardprogram.service.helper;

import com.assessment.rewardprogram.domain.request.PeriodRange;
import com.assessment.rewardprogram.domain.request.RewardPointsRequest;
import com.assessment.rewardprogram.domain.response.RewardPointsDetailsResponse;
import com.assessment.rewardprogram.entity.SalesOrderDetails;
import com.assessment.rewardprogram.repository.SalesOrderDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@Service
public class RewardPointsServiceImpl implements RewardPointsService {

    @Autowired
    private SalesOrderDetailsRepository salesOrderDetailsRepository;

    private String startDate;
    private String endDate;

    public RewardPointsServiceImpl() {
    }

    @Override
    public RewardPointsDetailsResponse calculateRewardPoints(RewardPointsRequest rewardPointsRequest) {

        getStartAndEndDate(rewardPointsRequest);


        List<SalesOrderDetails> salesOrderDetailsList = salesOrderDetailsRepository.findAllByCustomerId(rewardPointsRequest.getCustomerId());

        log.info("orders of 101: ", salesOrderDetailsList.toString());


        RewardPointsDetailsResponse pointsDetailsResponse = new RewardPointsDetailsResponse();
        pointsDetailsResponse.setCustomerId(rewardPointsRequest.getCustomerId());
        pointsDetailsResponse.setTotalRewardPoints(90.0);

        return pointsDetailsResponse;
    }

    private void getStartAndEndDate(RewardPointsRequest rewardPointsRequest) {
        PeriodRange range1 = rewardPointsRequest.getStartRange();
        PeriodRange range2 = rewardPointsRequest.getEndRange();

        // if range2 is null. then the request is for a given specific month. ex: 2022-05-01 to 2022-05-31.
        // else considers start and end date of given months. ex: 2022-05-01 to 2022-07-31.
        if (null == range2) {
            YearMonth yearMonth = YearMonth.of(Integer.parseInt(range1.getYear()), Month.valueOf(range1.getMonth().toUpperCase()));     // 2015-01. January of 2015.
            this.startDate = yearMonth.atDay(1).toString();     // ex: 2022-10-01
            this.endDate = yearMonth.atEndOfMonth().toString();  // ex: 2022-10-31

            log.debug("startDate: " + startDate);
            log.debug("endDate: " + endDate);
        } else {
            YearMonth yearMonthStart = YearMonth.of(Integer.parseInt(range1.getYear()),
                    Month.valueOf(range1.getMonth().toUpperCase()));     // 2015-01. January of 2015.
            this.startDate = yearMonthStart.atDay(1).toString();     // 2022-09-01

            YearMonth yearMonthEnd = YearMonth.of(Integer.parseInt(range2.getYear()),
                    Month.valueOf(range2.getMonth().toUpperCase()));     // 2015-01. January of 2015.
            this.endDate = yearMonthEnd.atEndOfMonth().toString();  // 2022-10-31

            log.debug("startDate: " + this.startDate);
            log.debug("endDate: " + this.endDate);
        }

    }
}
