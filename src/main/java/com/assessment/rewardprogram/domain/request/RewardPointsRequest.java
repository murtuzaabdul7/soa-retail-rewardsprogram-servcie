package com.assessment.rewardprogram.domain.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RewardPointsRequest {

    @JsonProperty("customerIds")
    private List<String> customerIds;

    @JsonProperty("startRange")
    private PeriodRange startRange;

    @JsonProperty("endRange")
    private PeriodRange endRange;

}
