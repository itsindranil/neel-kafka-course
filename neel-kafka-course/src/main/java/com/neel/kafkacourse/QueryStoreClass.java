package com.neel.kafkacourse;

import com.neel.kafkacourse.serde.AppSerdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class QueryStoreClass {
    static Logger logger = LoggerFactory.getLogger(QueryStoreClass.class);

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app");
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.setProperty(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreLocation);
        properties.setProperty(StreamsConfig.APPLICATION_SERVER_CONFIG, AppConfigs.queryServerHost+ ":" + AppConfigs.queryServerPort);
        //properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, AppSerdes.String().getClass().getName());
        //properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, AppSerdes.Employees().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();
        //Creating the Stream
       KStream<String, String> KS_AdClick = builder.stream("sample-topic", Consumed.with(AppSerdes.String(), AppSerdes.String()));

     // KT_AdInv.toStream().foreach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
        Topology topology = builder.build();

        KafkaStreams streams = new KafkaStreams(topology, properties);
        logger.info("Starting Streams");


        QueryServer queryServer = new QueryServer(streams, AppConfigs.queryServerHost, AppConfigs.queryServerPort);
        streams.setStateListener((newState, oldState) -> {
            logger.info("State Changing to " + newState + " from " + oldState);
            queryServer.setActive(newState == KafkaStreams.State.RUNNING && oldState == KafkaStreams.State.REBALANCING);
        });


        streams.start();
        TimeUnit.SECONDS.sleep(10);
        queryServer.start();
/*
        //Get the key value store
        ReadOnlyKeyValueStore<String, Regions> kvStore = streams.store("store-test_kt_join3", QueryableStoreTypes.keyValueStore());

        //Get all values for all keys
        KeyValueIterator<String, Regions> range = kvStore.all();
        while (range.hasNext()) {
            KeyValue<String, Regions> next = range.next();
            logger.info("Key: " + next.key + " value: " + next.value);
        }
*/
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting Down streams");
            queryServer.stop();
            streams.close();
        }));
    }
}
