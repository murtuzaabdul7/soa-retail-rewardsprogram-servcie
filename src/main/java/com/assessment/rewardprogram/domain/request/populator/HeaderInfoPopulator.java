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

        headerInfo.setApplicationid(headers.getFirst(RetailRewardsProgramServiceConstants.APPLICATIONID));
        headerInfo.setChannelid(headers.getFirst(RetailRewardsProgramServiceConstants.CHANNELID));
        headerInfo.setActivityid(headers.getFirst(RetailRewardsProgramServiceConstants.ACTIVITYID));

        headerInfo.setTimestamp(headers.getFirst(RetailRewardsProgramServiceConstants.TIMESTAMP));
        headerInfo.setInteractionid(headers.getFirst(RetailRewardsProgramServiceConstants.INTERACTIONID));
        headerInfo.setApplicationuserid(headers.getFirst(RetailRewardsProgramServiceConstants.APPLICATIONUSERID));

        headerInfo.setSenderid(headers.getFirst(RetailRewardsProgramServiceConstants.SENDER_ID_CODE));
        headerInfo.setSessionid(headers.getFirst(RetailRewardsProgramServiceConstants.SESSIONID));
    }
}
