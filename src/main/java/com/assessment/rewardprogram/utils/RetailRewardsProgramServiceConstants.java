package com.assessment.rewardprogram.utils;

public class RetailRewardsProgramServiceConstants {

    public static final String SPRINT_DEVICE_LOOKUP = "select * from sprintdevicedetails WHERE serialnumber=?1 OR\n" +
            "secondaryimei=?1 OR deviceserialnumber=?1";
    public static final String REPOSITORY_PATH = "com.assessment.rewardprogram.repository";
    public static final String REPO_BASEPATH = "com.assessment.rewardprogram";
    public static final String SPRINT = "Sprint";

    private RetailRewardsProgramServiceConstants() {

    }


    public static final String SERIAL_NUMBER = "serialNumber";
    public static final String SERIAL_NO_ALPHANUMERIC = "SERIALNOALPHANUMERIC";

    //	 URI MAPPINGS
    public static final String ERROR_MAPPING = "/error";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER_AUTH_KEYWORD = "Bearer ";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String INTERACTION_ID = "interaction-id";
    public static final String APPLICATION_ID = "application-id";
    public static final String ACTIVITY_ID = "activity-id";
    public static final String WORKFLOW_ID = "workflow-id";
    public static final String CHANNEL_ID = "channel-id";
    public static final String TIMESTAMP = "timestamp";
    public static final String APPLICATIONID = "applicationid";
    public static final String APPLICATIONUSERID = "applicationuserid";
    public static final String CHANNELID = "channelid";
    public static final String ACTIVITYID = "activityid";
    public static final String INTERACTIONID = "interactionid";
    public static final String DEALER_CODE = "dealercode";
    public static final String MASTER_DEALER_CODE = "masterdealercode";
    public static final String SESSIONID = "sessionid";
    public static final String SENDER_ID_CODE = "senderid";
    public static final String WORKFLOWID = "workflowid";
    public static final String STORE_ID = "storeid";


    //	ERROR CODES
    public static final String MANDATORY_HEADER_MESSAGE_ERROR_CODE = "SDD001";
    public static final String INVALID_SPECIAL_CHARACTERS_MESSAGE_ERROR_CODE = "SDD003";
    public static final String HEADER_VALIDATION_ERROR_CODE = "SDD002";
    public static final String HEADER_VALIDATION_ERROR_CODE_MESSAGE = "Invalid Header";
    public static final String NO_SERIAL_NUMBER_ERROR_CODE = "SDD400";
    public static final String RESOURCE_NOT_FOUND_ERROR_CODE = "SDD404";
    public static final String CONTENT_NOT_FOUND_ERROR_CODE = "SDD204";
    public static final String CONTENT_NOT_FOUND_ERROR_MESSAGE = "No Content Found for the Serialnumber";
    public static final String NO_SERIAL_NUMBER_ERROR_CODE_MESSAGE = "A valid serialNumber is required as an input";
    public static final String MANDATORY_ATTRIBUTE_MISSING_MESSAGE = "Mandatory Fields are missing in the request.";
    public static final String MANDATORY_HEADER_MISSING = " Mandatory header is invalid or missing. ";
    public static final String SERIAL_NOT_FOUND_ERROR_CODE = "SDD1002";
    public static final String SERIAL_NOT_FOUND_ERROR_USER_MESSAGE = "No content found";
    public static final String SERIAL_NOT_FOUND_ERROR_MESSAGE = "Sprint Device Details not found for the serialNumber: ";
    public static final String MAKEMODELDETAILS_BY_SKUID_API_CALL_EXCEPTION_CODE = "SDD004";
    public static final String MAKEMODELDETAILS_BY_SKUID_API_CALL_EXCEPTION_MESSAGE = "Exception while calling the makemodeldetailsbyskuid API";
}
