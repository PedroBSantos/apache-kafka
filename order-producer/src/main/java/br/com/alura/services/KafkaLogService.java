package br.com.alura.services;

import java.util.logging.Logger;

import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaLogService {

    public void log(RecordMetadata recordMetadata, Exception exception) {
        Logger.getGlobal().info(
                "Message published with success at topic: " + recordMetadata.topic()
                        + ", partition: " + recordMetadata.partition()
                        + " - " + recordMetadata.offset());
    }
}
