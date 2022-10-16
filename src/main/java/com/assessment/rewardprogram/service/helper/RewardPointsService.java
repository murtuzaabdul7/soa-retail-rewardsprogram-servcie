package com.assessment.rewardprogram.service.helper;


import com.assessment.rewardprogram.domain.request.RewardPointsRequest;
import com.assessment.rewardprogram.domain.response.RewardPointsDetailsResponse;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public interface RewardPointsService {

    RewardPointsDetailsResponse calculateRewardPoints(@NotNull RewardPointsRequest rewardPointsRequest);
}
