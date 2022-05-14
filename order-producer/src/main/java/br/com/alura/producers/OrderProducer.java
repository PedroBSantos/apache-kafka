package br.com.alura.producers;

import java.io.Closeable;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import br.com.alura.dtos.Order;
import br.com.alura.properties.ProducerProperties;
import br.com.alura.services.KafkaLogService;

public class OrderProducer implements Closeable {

    private final ProducerProperties producerProperties;
    private final KafkaProducer<UUID, Order> producer;
    private final KafkaLogService kafkaLogService;

    public OrderProducer(ProducerProperties producerProperties) {
        this.producerProperties = producerProperties;
        this.producer = new KafkaProducer<>(this.producerProperties.buildProperties());
        this.kafkaLogService = new KafkaLogService();
    }

    public void produce(UUID orderId, UUID userId, BigDecimal value) throws InterruptedException, ExecutionException {
        var order = new Order(orderId, userId, value);
        var orderRecord = new ProducerRecord<>(this.producerProperties.getTopic(), order.getUserId(), order);
        this.producer.send(orderRecord, this.kafkaLogService::log).get();
    }

    @Override
    public void close() {
        this.producer.close();
    }
}
