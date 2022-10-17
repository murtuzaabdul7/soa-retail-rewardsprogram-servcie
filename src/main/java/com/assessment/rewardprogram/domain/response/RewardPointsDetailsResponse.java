package com.assessment.rewardprogram.domain.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RewardPointsDetailsResponse {

    @JsonProperty("rewardPointsDetailsList")
    List<RewardPointsDetails> rewardPointsDetailsList;
}
