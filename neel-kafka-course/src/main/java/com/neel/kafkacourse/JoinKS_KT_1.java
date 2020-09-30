package com.neel.kafkacourse;


import com.neel.kafkacourse.serde.AppSerdes;
import com.neel.kafkacourse.types.AdInventories;
import com.neel.kafkacourse.types.AdOutSchema;
import com.neel.kafkacourse.types.Customers;
import com.neel.kafkacourse.types.Regions;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class JoinKS_KT_1 {
    public static void main(String[] args) throws InterruptedException {

        Logger logger = LoggerFactory.getLogger(JoinKS_KT_1.class);

        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app");
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.setProperty(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreLocation);

        StreamsBuilder builder = new StreamsBuilder();

        //Creating the KTable
        KTable<String, AdInventories> KT_AdInv = builder.table("topic-adInv",
                Materialized.<String, AdInventories, KeyValueStore<Bytes, byte[]>>as("store-AdInv")
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.AdInventories())
        );

        KTable<String, Customers> KT_Cust = builder.table("topic-customer",
                Materialized.<String, Customers, KeyValueStore<Bytes, byte[]>>as("store-customers")
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.Customers())
        );


        KTable<String, Regions> KT_Reg = builder.table("topic-regions",
                Materialized.<String, Regions, KeyValueStore<Bytes, byte[]>>as("store-regions")
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.Regions())
        );


        //Creating the Stream
        KStream<String, AdOutSchema> KS_AdTxn = builder.stream("topic-adTxn", Consumed.with(AppSerdes.String(), AppSerdes.AdOutSchema()));

        //Modifying the key to match the partition for Inventory
        KStream<String, AdOutSchema> KS_AdTxn_Inv = KS_AdTxn.map((key, value) -> KeyValue.pair(value.getInventoryID(), value));

        //Joining KStream to KTable for enrichment
        KStream<String, AdOutSchema> join_STG1 = KS_AdTxn_Inv.join(KT_AdInv, (v1, v2) -> {
                    AdOutSchema ad = new AdOutSchema();
                    ad.setTxnID(v1.getTxnID());
                    ad.setCustID(v1.getCustID());
                    ad.setRegID(v1.getRegID());
                    ad.setInventoryID(v1.getInventoryID());
                    ad.setNewsType(v2.getNewsType());
                    ad.setTxnVal(v1.getTxnVal());
                    return ad;
                }, Joined.with(AppSerdes.String(),AppSerdes.AdOutSchema(),AppSerdes.AdInventories()));

        //Modifying the key to join with Customer table
        KStream<String, AdOutSchema> KS_AdTxn_Cust = join_STG1.map((key, value) -> KeyValue.pair(value.getCustID(), value));

        //Joining Stage 2 join for Cust
        KStream<String, AdOutSchema> join_STG2 = KS_AdTxn_Cust.join(KT_Cust, (v1, v2) -> {
            AdOutSchema ad = new AdOutSchema();
            ad.setTxnID(v1.getTxnID());
            ad.setCustID(v1.getCustID());
            ad.setRegID(v1.getRegID());
            ad.setInventoryID(v1.getInventoryID());
            ad.setNewsType(v1.getNewsType());
            ad.setTxnVal(v1.getTxnVal());
            ad.setCustName(v2.getName());
            ad.setCustAddress(v2.getAddress());
            return ad;
        }, Joined.with(AppSerdes.String(),AppSerdes.AdOutSchema(),AppSerdes.Customers()));


        //Modifying the key to join with Region table
        KStream<String, AdOutSchema> KS_AdTxn_Reg = join_STG2.map((key, value) -> KeyValue.pair(value.getRegID(), value));

        //Joining Stage 2 join for Cust
        KStream<String, AdOutSchema> join_STG3 = KS_AdTxn_Reg.join(KT_Reg, (v1, v2) -> {
            AdOutSchema ad = new AdOutSchema();
            ad.setTxnID(v1.getTxnID());
            ad.setCustID(v1.getCustID());
            ad.setRegID(v1.getRegID());
            ad.setInventoryID(v1.getInventoryID());
            ad.setNewsType(v1.getNewsType());
            ad.setTxnVal(v1.getTxnVal());
            ad.setCustName(v1.getCustName());
            ad.setCustAddress(v1.getCustAddress());
            ad.setRegName(v2.getRegName());
            ad.setSubregName(v2.getSubregName());
            return ad;
        }, Joined.with(AppSerdes.String(),AppSerdes.AdOutSchema(),AppSerdes.Regions()));

        //KS_AdTxn_Inv.foreach((k, v) -> System.out.println("Key: " + k + " Value: " + v));

        //Printing out multi stage join output to sysout
        join_STG3.foreach((k, v) -> System.out.println("Key: " + k + " Value: " + v));


        Topology topology = builder.build();

        KafkaStreams streams = new KafkaStreams(topology, properties);
        logger.info("Starting Streams");

        streams.start();
        TimeUnit.SECONDS.sleep(10);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting Down streams");
            //queryServer.stop();
            streams.close();
        }));

    }
}
