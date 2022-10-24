package com.assessment.rewardprogram.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;


@UserDefinedType(value = "orderlineitemdetails")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderLineItem {

    @Column(value = "orderlineid")
    private String orderLineId;

    @Column(value = "productid")
    private String productId;

    @Column(value = "soldamount")
    private Double soldAmount;

    @Column(value = "status")
    private String status;

    @Column(value = "isrewardeligible")
    private boolean isRewardEligible;

}
