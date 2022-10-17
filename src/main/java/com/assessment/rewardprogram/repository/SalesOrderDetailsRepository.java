package com.assessment.rewardprogram.repository;


import com.assessment.rewardprogram.entity.SalesOrderDetails;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;


public interface SalesOrderDetailsRepository extends CrudRepository<SalesOrderDetails, Serializable> {

    @AllowFiltering
    List<SalesOrderDetails> findAllByCustomerId(String customerId);

    @Query("select * from salesorderdetails where customerid=?0 and createdon >=?1  " +
            "AND createdon<=?2 AND rewardpointseligible=?3 allow filtering")
    List<SalesOrderDetails> findAllByCustomerIdByRange(String customerId, Instant startDate, Instant endDate, boolean isRewardEligible);


}
