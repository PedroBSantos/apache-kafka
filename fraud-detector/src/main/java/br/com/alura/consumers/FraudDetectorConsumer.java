package br.com.alura.consumers;

import java.io.Closeable;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import br.com.alura.dtos.Order;
import br.com.alura.properties.ConsumerProperties;
import br.com.alura.services.FraudDetectorService;

public class FraudDetectorConsumer implements Closeable {

    private final KafkaConsumer<UUID, Order> consumer;
    private final ConsumerProperties consumerProperties;
    private final FraudDetectorService fraudDetectorService;

    public FraudDetectorConsumer(ConsumerProperties consumerProperties,
            FraudDetectorService fraudDetectorService) {
        this.consumerProperties = consumerProperties;
        this.consumer = new KafkaConsumer<>(this.consumerProperties.buildProperties());
        this.fraudDetectorService = fraudDetectorService;
    }

    public void consume() throws InterruptedException {
        this.consumer.subscribe(List.of(this.consumerProperties.getTopic()));
        while (true) {
            var records = this.consumer
                    .poll(Duration.ofMillis(this.consumerProperties.getPollDuration()));
            if (!records.isEmpty()) {
                records.forEach(record -> {
                    Logger.getGlobal().info(
                            "Received record from topic: " + record.topic()
                                    + ", partition: " + record.partition()
                                    + ", offset: " + record.offset());
                    this.fraudDetectorService.checkForFraud(record.value());
                });
            }
            Thread.sleep(1000);
        }
    }

    @Override
    public void close() {
        this.consumer.close();
    }
}
