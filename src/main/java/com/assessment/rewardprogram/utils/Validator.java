package com.assessment.rewardprogram.utils;


import com.assessment.rewardprogram.domain.request.HeaderInfo;
import com.assessment.rewardprogram.domain.request.RewardPointsRequest;
import com.assessment.rewardprogram.exception.InputValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

@Slf4j
@Component
public class Validator {

    @Autowired
    private HeaderInfo headerInfo;

    public void validateRequestObject(@NotNull RewardPointsRequest rewardPointsRequest) {
        validateMandatoryObjects("rewardPointsRequest", rewardPointsRequest);
        validateMandatoryObjects("customerId", rewardPointsRequest.getCustomerId());
        validateMandatoryObjects("startRange", rewardPointsRequest.getStartRange());
        validateMandatoryObjects("startRange.month", rewardPointsRequest.getStartRange().getMonth());
        validateMandatoryObjects("startRange.year", rewardPointsRequest.getStartRange().getYear());
    }

    private void validateMandatoryObjects(String objectName, Object objectBeingCompared) {
        if (!ObjectUtils.isEmpty(objectBeingCompared)) {
            if (objectBeingCompared instanceof String && isEmptyOrNull(String.valueOf(objectBeingCompared))) {
                // log alert
                String msg = "Invalid input key:" + objectName + " value: " + objectBeingCompared;
                createAlertLog(msg);
                throw new InputValidationException(RetailRewardsProgramServiceConstants.MANDATORY_ATTRIBUTE_MISSING_CODE,
                        RetailRewardsProgramServiceConstants.MANDATORY_ATTRIBUTE_MISSING_MESSAGE,
                        msg);
            }
        } else {
            // log alert
            String msg = "Invalid input key:" + objectName + " value: " + objectBeingCompared;
            createAlertLog(msg);
            throw new InputValidationException(RetailRewardsProgramServiceConstants.MANDATORY_ATTRIBUTE_MISSING_CODE,
                    RetailRewardsProgramServiceConstants.MANDATORY_ATTRIBUTE_MISSING_MESSAGE,
                    msg);
        }
    }

    private void createAlertLog(String msg) {
        String logMsg = AlertLog
                .generateAlert(RetailRewardsProgramServiceConstants.MANDATORY_ATTRIBUTE_MISSING_CODE,
                        RetailRewardsProgramServiceConstants.MANDATORY_ATTRIBUTE_MISSING_MESSAGE,
                        RetailRewardsProgramServiceConstants.SERVICE_NAME,
                        msg, headerInfo.toString())
                .toString();
        log.error(logMsg);
    }

    private boolean isEmptyOrNull(String value) {
        return value.equals("") || value.equalsIgnoreCase("null");
    }

}
