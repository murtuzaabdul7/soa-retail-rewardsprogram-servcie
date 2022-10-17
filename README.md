# soa-retail-rewardsprogram-servcie

A retailer offers a rewards program to its customers, awarding points based on each recorded
purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point
for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
Given a record of every transaction during a three month period, calculate the reward points
earned for each customer per month and total.


Assumptions made:
If a customer has multiple orders in a given range, then the rewards points are calculated based on individual PurchaseOrders and not Cumulative PurchaseOrderAmount.
For ex: 
Order1: 
PurchaseAmount: $70
Rewards: 20 points

Order2:
PurchaseAmount: $120
Rewards: 90 points

So the total reward points is 110 and not 230 (based on $190 total) .

Tech stack used:
Java 8
Spring Boot 2.7.2

Swagger location: https://github.com/murtuzaabdul7/soa-retail-rewardsprogram-servcie/blob/main/src/main/resources/swagger/RetailRewardsPoints.json
File can be viewed at: https://editor.swagger.io/

Datasource:
Apache Cassandra

CQL queries used:

CREATE TABLE localkeyspace.salesorderdetails (
salesordernumber text,
customerid text,
createdon timestamp,
createdby text,
lastupdatedby text,
lastupdateon timestamp,
orderlineitemlist list<frozen<orderlineitemdetails>>,
orderstatus text,
rewardpointseligible boolean,
saleschannel text,
totalpurchaseamount double,
PRIMARY KEY ((salesordernumber, customerid, createdon))
);

CREATE TYPE localkeyspace.orderlineitemdetails (
orderlineid text,
productid text,
soldamount double,
isrewardeligible boolean);


insert into salesorderdetails (salesordernumber,
customerid, saleschannel, createdby,
createdon, lastupdatedby, lastupdateon,
orderlineitemlist, totalpurchaseamount,
rewardpointseligible, orderstatus)
values ('102_002',
'102','DIGIORDER',
'soa-salesorder-consumer-service','2022-08-12T18:00:00+00:00',
'DIGIORDER','2022-08-12T18:00:00+00:00',
[{orderlineid:'11', productid:'PID137', soldamount: 100.0, status:'DELIVERED', isrewardeligible:true}],
100.0, true,'COMPLETED');


Example query for order filering:
select * from salesorderdetails where customerid='101' and createdon >= '2020-08-01 00:00:00+0000'  AND createdon <= '2022-08-30 00:00:00+0000' allow filtering;    
    
CQLSH snapshot
![image](https://user-images.githubusercontent.com/115746670/196281386-a7a0d8f8-58b1-416e-a9f3-38814ff51cd5.png)



   
curl --location --request POST 'localhost:8080/rewards-program/v1/reward-points-details' \
--header 'applicationid: RETAIL' \
--header 'activityid: c34e7acd-384b-4c22-8b02-ba396368250' \
--header 'workflowid: DIGORDER' \
--header 'interactionid: c34e7acd-384b-4c22-8b02-ba3963681351' \
--header 'timestamp: 2022-10-13T12:55:02Z' \
--header 'Content-Type: application/json' \
--data-raw '{
    "customerIds": ["101","102"],
    "startRange": {
        "year": "2022",
        "month": "AUGUST"
    },
    "endRange": {
        "year": "2022",
        "month": "DECEMBER"
    }
}'


Sample Response:
{
    "rewardPointsDetailsList": [
        {
            "customerId": "101",
            "startDate": "2022-08-01T00:00:00.000000001Z",
            "endDate": "2022-12-31T23:59:59.999999999Z",
            "totalRewardPoints": 170.0,
            "totalPurchaseOrders": 4,
            "totalPurchaseOrderAmount": 350.0
        },
        {
            "customerId": "102",
            "startDate": "2022-08-01T00:00:00.000000001Z",
            "endDate": "2022-12-31T23:59:59.999999999Z",
            "totalRewardPoints": 70.0,
            "totalPurchaseOrders": 2,
            "totalPurchaseOrderAmount": 170.0
        }
    ]
}
 
[Postman Collection](https://github.com/murtuzaabdul7/soa-retail-rewardsprogram-servcie/blob/main/src/main/resources/soa-retail-rewardsprogram-servcie.postman_collection.json)
    
   Postman snapshot
![image](https://user-images.githubusercontent.com/115746670/196280701-37cfde05-fe82-4b13-8815-47971efb48ac.png)

![image](https://user-images.githubusercontent.com/115746670/196282027-acb62a91-a363-4e19-9d32-a0cbf7cb7f98.png)

                                                                                                                         
 Parameterized Tests Success

![image](https://user-images.githubusercontent.com/115746670/196282289-8c99b451-8c68-4e10-a216-a5d17d603366.png)
                                                                                        
   
  
