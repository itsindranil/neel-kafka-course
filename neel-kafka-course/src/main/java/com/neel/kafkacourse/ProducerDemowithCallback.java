package com.neel.kafkacourse;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

public class ProducerDemowithCallback {

    public static void main(String[] args) {
        String bootstrapserver = "neel-pc:9092";

        final Date curr_date = new Date();
        long curr_time = curr_date.getTime();
        Timestamp curr_ts = new Timestamp(curr_time);


        final Logger logger = LoggerFactory.getLogger(ProducerDemowithCallback.class);

        //Config

        final Properties property = new Properties();
        property.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapserver);
        property.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        property.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Create prodcuer
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(property);

        //create a producer record
        ProducerRecord<String, String> record =
                new ProducerRecord<String, String>("topic1", "Hello from Java @ " + curr_ts);

        //send the record
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    logger.info("Received message now.  \n" +
                            "Topic : " + recordMetadata.topic() + "\n" +
                            "Partition : " + recordMetadata.partition() + "\n" +
                            "Offset : " + recordMetadata.offset() + "\n" +
                            "Timestamp : " + recordMetadata.timestamp()
                    );
                } else {
                    logger.error("Error while producing", e);
                }

            }

        });

        //flush and close producer

        producer.flush();
        producer.close();


    }

}
