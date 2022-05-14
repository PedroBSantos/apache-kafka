package br.com.alura.producers;

import java.io.Closeable;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import br.com.alura.dtos.Email;
import br.com.alura.properties.ProducerProperties;
import br.com.alura.services.KafkaLogService;

public class EmailProducer implements Closeable {

    private final ProducerProperties producerProperties;
    private final KafkaProducer<UUID, Email> producer;
    private final KafkaLogService kafkaLogService;

    public EmailProducer(ProducerProperties producerProperties) {
        this.producerProperties = producerProperties;
        this.producer = new KafkaProducer<>(this.producerProperties.buildProperties());
        this.kafkaLogService = new KafkaLogService();
    }

    public void produce(UUID emailId, String address, String content) throws InterruptedException, ExecutionException {
        var email = new Email(emailId, address, content);
        var orderRecord = new ProducerRecord<>(this.producerProperties.getTopic(), email.getId(), email);
        this.producer.send(orderRecord, this.kafkaLogService::log).get();
    }

    @Override
    public void close() {
        this.producer.close();
    }
}
