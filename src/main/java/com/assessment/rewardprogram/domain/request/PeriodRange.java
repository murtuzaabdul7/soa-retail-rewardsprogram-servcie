package com.assessment.rewardprogram.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PeriodRange {

    @JsonProperty("year")
    private String year;

    @JsonProperty("month")
    private String month;
}
