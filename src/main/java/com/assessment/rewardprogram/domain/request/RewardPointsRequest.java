package com.assessment.rewardprogram.domain.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RewardPointsRequest {

    @JsonProperty("customerIds")
    private List<String> customerIdList;

    @JsonProperty("startRange")
    private PeriodRange startRange;

    @JsonProperty("endRange")
    private PeriodRange endRange;

}
