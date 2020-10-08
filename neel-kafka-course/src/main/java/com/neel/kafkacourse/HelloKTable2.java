package com.neel.kafkacourse;

import com.neel.kafkacourse.serde.AppSerdes;
import com.neel.kafkacourse.serde.JsonSerializer;
import com.neel.kafkacourse.types.Regions;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Printed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class HelloKTable2 {
    static Logger logger = LoggerFactory.getLogger(HelloKTable2.class);

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app-2");
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        //properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, AppSerdes.String().getClass().getName());
        //properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, AppSerdes.Employees().getClass().getName());
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());


        KafkaProducer<String, Regions> producer = new KafkaProducer<String, Regions>(properties);

        StreamsBuilder builder = new StreamsBuilder();
        KTable<String, Regions> KT1 = builder.table("streams-app-store-regions-raw-changelog", Consumed.with(AppSerdes.String(), AppSerdes.Regions()));

       // KTable KT2 = KT1.toStream().groupByKey().count();

        //streams-app-store-regions-raw-changelog

        KT1.toStream().print(Printed.<String, Regions>toSysOut().withLabel("KT1"));

        Topology topology = builder.build();

        KafkaStreams streams = new KafkaStreams(topology, properties);
        logger.info("Starting Streams");


        streams.start();
       // TimeUnit.SECONDS.sleep(10);
        //queryServer.start();




        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting Down streams");
            //queryServer.stop();
            streams.close();
        }));


    }
}
