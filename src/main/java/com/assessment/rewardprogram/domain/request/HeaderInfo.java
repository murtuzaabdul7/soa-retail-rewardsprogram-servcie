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
    private String activityid;
    @JsonProperty
    private String interactionid;

    @JsonProperty
    private String workdlowid;
    @JsonProperty
    private String timestamp;

    @Override
    public String toString() {
        return MessageFormat.format("Headerinfo (applicationid={0}, " +
                        "activityid={1}," +
                        "interactionid={2}," +
                        "workflowid={3}," +
                        "timestamp={4})",
                replaceMissingHeadersWithNull(applicationid), replaceMissingHeadersWithNull(activityid),
                replaceMissingHeadersWithNull(interactionid), replaceMissingHeadersWithNull(workdlowid),
                replaceMissingHeadersWithNull(timestamp));
    }

    private String replaceMissingHeadersWithNull(String value) {
        return ObjectUtils.isEmpty(value) ? "null" : value;
    }
}
