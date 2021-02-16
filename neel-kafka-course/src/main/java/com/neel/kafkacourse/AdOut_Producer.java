package com.neel.kafkacourse;

import com.neel.kafkacourse.serde.JsonSerializer;
import com.neel.kafkacourse.types.AdOut;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class AdOut_Producer {
    public static void main(String[] args) throws InterruptedException {
        final Date curr_date = new Date();
        long curr_time = curr_date.getTime();
        Timestamp curr_ts = new Timestamp(curr_time);
        Random random = new Random();

        String topic = "test_kt_join42";


        //Logger
        final Logger logger = LoggerFactory.getLogger(AdOut_Producer.class);

        //Config

        final Properties property = new Properties();
        property.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        property.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        property.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        //Create producer
        final KafkaProducer<String, AdOut> producer = new KafkaProducer<String, AdOut>(property);
        AdOut adOut = new AdOut();
        for (int i = 1; i <= 20; i++) {
            int rand1 = random.nextInt(1000);
            int rand2 = random.nextInt(200);
            int rand3 = random.nextInt(100);
            int rand4 = random.nextInt(50);
            Double val = random.nextDouble();
            String key = "Neel-"+Integer.toString(i);
            //String  Employees.= "NewlyUpdated-"+Integer.toString(i);
            // if (i % 20000 == 0) {
            //     TimeUnit.SECONDS.sleep(3);
            // }


            adOut.setInventoryID("AdOut-"+Integer.toString(rand3));
            adOut.setNewsType("AdOutType-"+Integer.toString(rand4));



            //create a producer record with Keys
            ProducerRecord<String, AdOut> record =
                    new ProducerRecord<String, AdOut>(topic, key, adOut);

            //send the record
            int finalI = i;
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        logger.info("Record " + record.key()+" , "+ record.value() + " now.  \n" +
                                "Sending message Run " + Integer.toString(finalI) + " now.  \n" +
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
