package com.assessment.rewardprogram.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;

@Component
@Getter
@Setter
public class HeaderInfo {

    @JsonProperty
    private String applicationid;
    @JsonProperty
    private String channelid;
    @JsonProperty
    private String activityid;
    @JsonProperty
    private String interactionid;
    @JsonProperty
    private String applicationuserid;
    @JsonProperty
    private String senderid;
    @JsonProperty
    private String sessionid;
    @JsonProperty
    private String timestamp;

    @Override
    public String toString() {
        return MessageFormat.format("Headerinfo (applicationid={0}, " +
                        "channelid={1}, " +
                        "activityid={2}," +
                        "interactionid={3}," +
                        "applicationuserid={4}," +
                        "senderid={5}," +
                        "sessionid={6}," +
                        "timestamp={7})",
                replaceMissingHeadersWithNull(applicationid),
                replaceMissingHeadersWithNull(channelid), replaceMissingHeadersWithNull(activityid),
                replaceMissingHeadersWithNull(interactionid), replaceMissingHeadersWithNull(applicationuserid),
                replaceMissingHeadersWithNull(senderid), replaceMissingHeadersWithNull(sessionid),
                replaceMissingHeadersWithNull(timestamp));
    }

    private String replaceMissingHeadersWithNull(String value) {
        return ObjectUtils.isEmpty(value) ? "null" : value;
    }
}
