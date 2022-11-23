package com.manyroads.dev.constants;

public class Constants {

    // *** Constants declaration ***
    // Configuration
    public static final String STR_URL_BROKER       = "vm://localhost:8000";
    public static final String STR_DESTINATION      = "TestDestination1";

    // Messages from Producers and Consumers
    public static final String STR_MSSG_PROD        = "Test message from: ";
    public static final String STR_MSSG_PROD_CMD    = "Sent message: ";
    public static final String STR_MSSG_CNSM        = "Message received: ";

    // Pre- Postfix
    public static final String STR_COLON            = " : ";

    // Error messages
    public static final String STR_MSSG_ERROR_PROD  = "Caught: ";
    public static final String STR_MSSG_ERROR_CNSM  = "JMS Exception occured.  Shutting down client.";








}
