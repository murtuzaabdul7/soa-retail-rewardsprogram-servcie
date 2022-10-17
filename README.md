# soa-retail-rewardsprogram-servcie

A retailer offers a rewards program to its customers, awarding points based on each recorded
purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point
for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three month period, calculate the reward points
earned for each customer per month and total.

Tech stack used:
Java 8
Spring Boot 2.7.2

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