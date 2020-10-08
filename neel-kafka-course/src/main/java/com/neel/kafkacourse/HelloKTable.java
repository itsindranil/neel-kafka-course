package com.neel.kafkacourse;


import com.neel.kafkacourse.serde.AppSerdes;
import com.neel.kafkacourse.serde.JsonSerializer;
import com.neel.kafkacourse.types.Regions;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class HelloKTable {
    static Logger logger = LoggerFactory.getLogger(HelloKTable.class);

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.streamsappID);
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        //properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, AppSerdes.String().getClass().getName());
        //properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, AppSerdes.Employees().getClass().getName());
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());


        KafkaProducer<String, Regions> producer = new KafkaProducer<String, Regions>(properties);

        StreamsBuilder builder = new StreamsBuilder();

        KTable<String, Regions> KT0 = builder.table("topic-region-raw",
                Materialized.<String, Regions, KeyValueStore<Bytes, byte[]>>as("store-regions-raw")
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.Regions())
        );

        //KTable<String, Regions> KT0 = builder.table("topic-region-in", Consumed.with(AppSerdes.String(), AppSerdes.Regions()));
        //KT0.toStream().print(Printed.<String, Regions>toSysOut().withLabel("KT0"));
        //KT0.toStream().to("topic-region-out");
        //create a producer record with Keys


/*
        KTable<String, Employees> KT1 = KT0.filter((k, v) -> k.matches(AppConfigs.regExSymbol) ,
                Materialized.<String, Employees, KeyValueStore<Bytes, byte[]>>as(AppConfigs.stateStoreName)
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.Employees())
        );
        KT1.toStream().print(Printed.<String, Employees>toSysOut().withLabel("KT1"));
        KT1.toStream().to("out-topic6");
*/
        Topology topology = builder.build();

        KafkaStreams streams = new KafkaStreams(topology, properties);
        logger.info("Starting Streams");

/*
        QueryServer queryServer = new QueryServer(streams, AppConfigs.queryServerHost, AppConfigs.queryServerPort);
        streams.setStateListener((newState, oldState) -> {
            logger.info("State Changing to " + newState + " from " + oldState);
            queryServer.setActive(newState == KafkaStreams.State.RUNNING && oldState == KafkaStreams.State.REBALANCING);
        });
*/

        streams.start();
        TimeUnit.SECONDS.sleep(10);
        //queryServer.start();

        //Get the key value store
        ReadOnlyKeyValueStore<String, Regions> kvStore = streams.store("store-regions-raw", QueryableStoreTypes.keyValueStore());

        //Get all values for all keys
        KeyValueIterator<String, Regions> range = kvStore.all();
        while (range.hasNext()) {
            KeyValue<String, Regions> next = range.next();
            ProducerRecord<String, Regions> record =
                    new ProducerRecord<String, Regions>("topic-region-out", next.key, next.value);
            producer.send(record);
            logger.info("Key: " + next.key + " value: " + next.value);
        }

        //KTable<String, Regions> KT1 = builder.table("topic-region-out", Consumed.with(AppSerdes.String(), AppSerdes.Regions()));
        //KT1.toStream().print(Printed.<String, Regions>toSysOut().withLabel("KT1"));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting Down streams");
            //queryServer.stop();
            streams.close();
        }));


    }
}
