package com.assessment.rewardprogram.domain.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RewardPointsDetailsResponse {

    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("totalRewardPoints")
    private Double totalRewardPoints;

}
