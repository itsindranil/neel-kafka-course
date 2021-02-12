package com.neel.kafkacourse;

import com.neel.kafkacourse.serde.AppSerdes;
import com.neel.kafkacourse.types.AdInventories;
import com.neel.kafkacourse.types.AdOutSchema;
import com.neel.kafkacourse.types.AdTxn;
import com.neel.kafkacourse.types.Regions;
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

public class Joins_KT_KT {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(Joins_KT_KT.class);


        Properties property = new Properties();
        property.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app");
        property.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        property.setProperty(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreLocation);

        StreamsBuilder builder = new StreamsBuilder();

        //Creating the KTable
        KTable<String, AdInventories> KT_AdInv = builder.table("test_kt_join1",
                Materialized.<String, AdInventories, KeyValueStore<Bytes, byte[]>>as("store-test_kt_join1")
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.AdInventories())
        );

        //Creating the KTable
        KTable<String, AdTxn> KT_AdTxn = builder.table("test_kt_join2",
                Materialized.<String, AdTxn, KeyValueStore<Bytes, byte[]>>as("store-test_kt_join2")
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.AdTxn())
        );

        KTable<String, AdOutSchema> joined_KT = KT_AdInv.join(KT_AdTxn, (v1,v2) -> {
            AdOutSchema ad = new AdOutSchema();
            ad.setInventoryID(v1.getInventoryID());
            return ad;
        }, Materialized.<String, AdOutSchema, KeyValueStore<Bytes, byte[]>>as("store-test_kt_join3")
                .withKeySerde(AppSerdes.String())
                .withValueSerde(AppSerdes.AdOutSchema()));

        Topology topology = builder.build();

        KafkaStreams streams = new KafkaStreams(topology, property);
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
        ReadOnlyKeyValueStore<String, Regions> kvStore = streams.store("store-test_kt_join3", QueryableStoreTypes.keyValueStore());

        //Get all values for all keys
        KeyValueIterator<String, Regions> range = kvStore.all();
        while (range.hasNext()) {
            KeyValue<String, Regions> next = range.next();
            //ProducerRecord<String, Regions> record =
            //        new ProducerRecord<String, Regions>("topic-region-out", next.key, next.value);
            //producer.send(record);
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
