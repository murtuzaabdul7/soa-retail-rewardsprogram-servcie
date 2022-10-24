package com.assessment.rewardprogram.utils;

import com.assessment.rewardprogram.domain.request.HeaderInfo;
import com.assessment.rewardprogram.domain.request.PeriodRange;
import com.assessment.rewardprogram.domain.request.RewardPointsRequest;
import com.assessment.rewardprogram.exception.InputValidationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ValidatorTest {

    @Mock
    private HeaderInfo headerInfo;

    @InjectMocks
    private Validator validator;

    @ParameterizedTest
    @CsvFileSource(resources = "/MandatoryAttributeValidatorTest.csv", numLinesToSkip = 1)
    public void validateRequestObjectTest(String customerId, String startRangeObject, String startMonth,
                                          String startYear, String expectedSystemMessage, String errorCode) {
        RewardPointsRequest rewardPointsRequest = new RewardPointsRequest();
        if (customerId == null) {
            rewardPointsRequest.setCustomerIds(null);
        } else {
            rewardPointsRequest.setCustomerIds(Collections.singletonList(customerId));
        }

        if (startRangeObject == null) {
            rewardPointsRequest.setStartRange(null);
        } else {
            rewardPointsRequest.setStartRange(new PeriodRange(startYear, startMonth));
        }
        InputValidationException inputValidationException = null;
        if (!errorCode.equals("NA")) {
            inputValidationException = assertThrows(InputValidationException.class, () -> {
                validator.validateRequestObject(rewardPointsRequest);
            });
        }
        if (expectedSystemMessage.equals("NA")) {
            // no missing attributes so no exception triggered.
            log.info("All mandatory attributes are present");
            assertNull(inputValidationException);
        } else {
            assertEquals("ERR001", inputValidationException.getCode());
            assertEquals(expectedSystemMessage, inputValidationException.getSystemMessage());
            assertEquals("Mandatory attribute is missing", inputValidationException.getUserMessage());
        }
    }
}
