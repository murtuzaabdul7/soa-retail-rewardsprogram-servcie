package com.assessment.rewardprogram.integrationtests;

import com.assessment.rewardprogram.domain.request.PeriodRange;
import com.assessment.rewardprogram.domain.request.RewardPointsRequest;
import com.assessment.rewardprogram.utils.RetailRewardsProgramServiceConstants;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author murtuza
 */

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class RewardPointsDetailsControllerITTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void getRewardPoints_200_HappyPath() throws Exception {

        String content = convertJson(createRequestObject("101", "AUGUST", "2022"));
        mockMvc.perform(post("/reward-points-details")
                        .headers(getHeaders())
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("rewardPointsDetailsList[0].customerId").value("101"))
                .andExpect(jsonPath("rewardPointsDetailsList[0].startDate").value("2022-08-01T00:00:00.000000001Z"))
                .andExpect(jsonPath("rewardPointsDetailsList[0].endDate").value("2022-08-31T23:59:59.999999999Z"))
                .andExpect(jsonPath("rewardPointsDetailsList[0].totalPurchaseOrderAmount").value(70.0))
                .andExpect(jsonPath("rewardPointsDetailsList[0].totalRewardPoints").value(20.0));


    }

    @Test
    public void getBrandDevice_404_NotFound() throws Exception {
        mockMvc.perform(post("/reward-points-details1234")
                        .headers(getHeaders())
                        .content(convertJson(createRequestObject("101", "JUNE", "2022")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getBrandDevice_405_MethodNotAllowed() throws Exception {
        mockMvc.perform(get("/reward-points-details")
                        .headers(getHeaders())
                        .content(convertJson(createRequestObject("101", "JUNE", "2022")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("405"))
                .andExpect(jsonPath("userMessage").value("Method Not Allowed"))
                .andExpect(jsonPath("systemMessage").value("Method Not Allowed"));
    }

    @Test
    public void getBrandDevice_415_MediaTypeNotSupported() throws Exception {
        HttpHeaders headers = getHeaders();
        headers.remove(RetailRewardsProgramServiceConstants.CONTENT_TYPE);
        headers.add(RetailRewardsProgramServiceConstants.CONTENT_TYPE, "text/xml");

        mockMvc.perform(post("/reward-points-details")
                        .headers(headers)
                        .content(convertJson(createRequestObject("101", "JUNE", "2022")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("415"))
                .andExpect(jsonPath("userMessage").value("MediaType Not Supported"))
                .andExpect(jsonPath("systemMessage").value("MediaType Not Supported"));
    }

    @Test
    public void getBrandDevice_MandatoryFieldMissing() throws Exception {
        mockMvc.perform(post("/reward-points-details")
                        .headers(getHeaders())
                        .content(convertJson(createRequestObject(null, "JUNE", "2022")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value(RetailRewardsProgramServiceConstants.MANDATORY_ATTRIBUTE_MISSING_CODE))
                .andExpect(jsonPath("userMessage").value(RetailRewardsProgramServiceConstants.MANDATORY_ATTRIBUTE_MISSING_MESSAGE))
                .andExpect(jsonPath("systemMessage").value("Invalid input key:customerIds.customerId value: null"));
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(RetailRewardsProgramServiceConstants.AUTHORIZATION, "12345");
        httpHeaders.set(RetailRewardsProgramServiceConstants.APPLICATION_ID, "RETAIL");
        httpHeaders.set(RetailRewardsProgramServiceConstants.ACTIVITY_ID, "c34e7acd-384b-4c22-8b02-ba396368250");
        httpHeaders.set(RetailRewardsProgramServiceConstants.WORKFLOW_ID, "RewardsProgram");
        httpHeaders.set(RetailRewardsProgramServiceConstants.CONTENT_TYPE, "application/json");
        return httpHeaders;
    }

    private String convertJson(RewardPointsRequest rewardPointsRequest) {
        Gson gson = new Gson();
        return gson.toJson(rewardPointsRequest, RewardPointsRequest.class);
    }

    private RewardPointsRequest createRequestObject(String customerId, String startMonth, String startYear) {
        RewardPointsRequest rewardPointsRequest = new RewardPointsRequest();
        rewardPointsRequest.setCustomerIds(Collections.singletonList(customerId));
        rewardPointsRequest.setStartRange(new PeriodRange(startYear, startMonth));
        return rewardPointsRequest;
    }

}
