package com.assessment.rewardprogram.entity;


import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Frozen;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Table(value = "salesorderdetails")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SalesOrderDetails {

    @PrimaryKey
    @Column(value = "salesordernumber")
    private String salesOrderNumber;

    @Column(value = "customerid")
    private String customerId;

    @Column(value = "saleschannel")
    private String salesChannel;

    @Column(value = "createdby")
    private String createdBy;

    @Column(value = "createdon")
    private LocalDateTime createdOn;

    @Column(value = "lastupdatedby")
    private String lastUpdatedBy;

    @Column(value = "lastupdatedon")
    private LocalDateTime lastUpdatedOn;

    @Column(value = "orderlineitemlist")
    private List<@Frozen OrderLineItem> orderLineItemList;

    @Column(value = "totalpurchaseamount")
    private Double totalPurchaseAmount;

    @Column(value = "rewardpointseligible")
    private boolean rewardPointsEligible;

    @Column(value = "orderstatus")
    private String orderStatus;

}
