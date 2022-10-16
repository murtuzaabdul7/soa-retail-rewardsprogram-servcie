package com.assessment.rewardprogram.controller;

import com.assessment.rewardprogram.domain.request.HeaderInfo;
import com.assessment.rewardprogram.domain.request.RewardPointsRequest;
import com.assessment.rewardprogram.domain.request.populator.HeaderInfoPopulator;
import com.assessment.rewardprogram.domain.response.RewardPointsDetailsResponse;
import com.assessment.rewardprogram.service.helper.RewardPointsServiceImpl;
import com.assessment.rewardprogram.utils.Validator;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

@Slf4j
@RestController
@RefreshScope
public class RewardPointsDetailsController {

    @Autowired
    private HeaderInfoPopulator headerInfoPopulator;

    @Autowired
    private RewardPointsServiceImpl rewardPointsService;

    @Autowired
    private HeaderInfo headerInfo;

    @Autowired
    private Validator validator;

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "SUCCESS_RESPONSE"),
            @ApiResponse(responseCode = "204", description = "No Content Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorised Access"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found"),
            @ApiResponse(responseCode = "415", description = "MediaType Not Supported"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")})
    @PostMapping(value = "${reward.points.details.mapping}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public RewardPointsDetailsResponse getRewardPointsDetails(@RequestHeader HttpHeaders headers,
                                                              @NotNull(message = "Request Body Cannot Be Null.")
                                                              @RequestBody RewardPointsRequest rewardPointsRequest) {
        log.info("Controller: getRewardPointsDetails request object {}", rewardPointsRequest);

        headerInfoPopulator.populate(headers);
        log.info("headerInfo: " + headerInfo);

        // validate request object
        validator.validateRequestObject(rewardPointsRequest);

        return rewardPointsService.calculateRewardPoints(rewardPointsRequest);
    }
}
