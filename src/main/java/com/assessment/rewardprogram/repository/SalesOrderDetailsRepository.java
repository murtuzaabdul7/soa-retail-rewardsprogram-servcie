package com.assessment.rewardprogram.repository;


import com.assessment.rewardprogram.entity.SalesOrderDetails;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;


public interface SalesOrderDetailsRepository extends CrudRepository<SalesOrderDetails, Serializable> {

    @AllowFiltering
    List<SalesOrderDetails> findAllByCustomerId(String customerId);


}
