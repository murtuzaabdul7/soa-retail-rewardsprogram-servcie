package com.assessment.rewardprogram.domain.request.populator;

import com.assessment.rewardprogram.domain.request.HeaderInfo;
import com.assessment.rewardprogram.utils.RetailRewardsProgramServiceConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HeaderInfoPopulator {

    @Autowired
    private HeaderInfo headerInfo;

    public void populate(HttpHeaders headers) {

        headerInfo.setApplicationid(headers.getFirst(RetailRewardsProgramServiceConstants.APPLICATION_ID));
        headerInfo.setActivityid(headers.getFirst(RetailRewardsProgramServiceConstants.ACTIVITY_ID));

        headerInfo.setTimestamp(headers.getFirst(RetailRewardsProgramServiceConstants.TIMESTAMP));
        headerInfo.setInteractionid(headers.getFirst(RetailRewardsProgramServiceConstants.INTERACTION_ID));

        headerInfo.setWorkdlowid(headers.getFirst(RetailRewardsProgramServiceConstants.WORKFLOW_ID));
    }
}
