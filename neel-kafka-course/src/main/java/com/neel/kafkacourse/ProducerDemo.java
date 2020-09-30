package com.neel.kafkacourse;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(ProducerDemo.class);
        logger.info("Starting Producer");

        //Config

        Properties property = new Properties();
        property.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        property.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        property.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Create prodcuer

        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(property);

        logger.info("Starting sending messages");
        for (int i = 0; i <= AppConfigs.inputRows; i++) {
            //create a producer record
            ProducerRecord<Integer, String> record =
                    new ProducerRecord<Integer, String>(AppConfigs.topicName, i, "Message-" + Integer.toString(i));
            //send the record
            producer.send(record);
        }
        //flush and close producer
        // producer.flush();
        logger.info("Finished Sending messages");
        producer.close();


    }

}
