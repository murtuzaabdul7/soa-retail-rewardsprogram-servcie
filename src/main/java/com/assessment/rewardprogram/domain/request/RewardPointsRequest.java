package com.assessment.rewardprogram.domain.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RewardPointsRequest {

    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("startRange")
    private PeriodRange startRange;

    @JsonProperty("endRange")
    private PeriodRange endRange;

}
