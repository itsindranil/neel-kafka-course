package com.neel.kafka.streams;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;

import java.util.Properties;

public class StreamsApp1 {
    public static void main(String[] args) {
        Properties config = new Properties();
        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "stream-app-1");
        config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "neel-pc:9092");
        config.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        config.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> stream1 = builder.stream("topic5");
        // do stuff
        //kStream.to("topic-out-kstream");
        // do stuff
        //table1.toStream().groupByKey().reduce((val1, val2) -> val2).toStream().to("topic-out-kstream");
        KGroupedStream<String, String> grpstream1 = stream1.groupByKey();

        KTable<String, Long> outstream = grpstream1.count(Materialized.as("cnt"));

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();

        ReadOnlyKeyValueStore<String, Long> keyValueStore = streams.store();

        System.out.println("Count for 1 is "+ keyValueStore.get("1"));
        // print the topology
        //streams.localThreadsMetadata().forEach(data -> System.out.println(data));

        // shutdown hook to correctly close the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}
