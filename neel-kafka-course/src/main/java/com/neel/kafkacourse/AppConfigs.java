package com.neel.kafkacourse;

public class AppConfigs {
    final static String applicationID = "Sample-Producer";
    final static String streamsappID = "streams-app";
    final static String bootstrapServers = "neel-pc:9091, neel-pc:9092, neel-pc:9093";
    final static String topicName = "topic6";
    final static int inputRows = 100;
    final static String stateStoreLocation = "/tmp/state-store";
    final static String stateStoreName = "KT0-neel-store";
    final static String regExSymbol = "(?i)101|102|103";
    final static String queryServerHost = "localhost";
    final static int queryServerPort = 7010;
}
