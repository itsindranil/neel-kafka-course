package com.neel.kafkacourse;

import com.neel.kafkacourse.serde.JsonSerializer;
import com.neel.kafkacourse.types.Regions;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class Region_Producer {

    public static void main(String[] args) throws InterruptedException {
        final Date curr_date = new Date();
        long curr_time = curr_date.getTime();
        Timestamp curr_ts = new Timestamp(curr_time);
        Random random = new Random();

        String topic = "topic-regions";


        //Logger
        final Logger logger = LoggerFactory.getLogger(Region_Producer.class);

        //Config

        final Properties property = new Properties();
        property.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        property.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        property.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        //Create producer
        final KafkaProducer<String, Regions> producer = new KafkaProducer<String, Regions>(property);
        Regions regions = new Regions();
        for (int i = 1; i <= 50; i++) {
            int rand1 = random.nextInt(1000);
            int rand2 = random.nextInt(1000);
            String key = "R-"+Integer.toString(i);
/*            //String  Employees.= "NewlyUpdated-"+Integer.toString(i);
            if (i % 200 == 0) {
                TimeUnit.SECONDS.sleep(10);
            }
*/
            regions.setRegID("R-"+Integer.toString(i));
            regions.setRegName("Reg-"+Integer.toString(i%5));
            regions.setSubregName("Sub-Reg-"+Integer.toString(i));


            //create a producer record with Keys
            ProducerRecord<String, Regions> record =
                    new ProducerRecord<String, Regions>(topic, key, regions);

            //send the record
            int finalI = i;
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        logger.info("Sending message Run " + Integer.toString(finalI) + " now.  \n" +
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

        }

        //flush and close producer

        producer.flush();
        producer.close();
    }
}
