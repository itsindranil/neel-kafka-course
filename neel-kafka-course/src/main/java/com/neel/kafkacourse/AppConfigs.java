package com.neel.kafkacourse;

public class AppConfigs {
    final static String applicationID = "Sample-Producer";
    final static String streamsappID = "streams-app";
    final static String bootstrapServers = "neel-pc:9092";
    final static String topicName = "topic6";
    final static int inputRows = 100;
    final static String stateStoreLocation = "/tmp/state-store";
    final static String stateStoreName = "store-test_kt_join45";
    final static String regExSymbol = "(?i)101|102|103";
    final static String queryServerHost = "localhost";
    final static int queryServerPort = 7010;
}
