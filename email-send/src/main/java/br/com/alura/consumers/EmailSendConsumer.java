package br.com.alura.consumers;

import java.io.Closeable;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import br.com.alura.dtos.Email;
import br.com.alura.properties.ConsumerProperties;
import br.com.alura.services.EmailService;

public class EmailSendConsumer implements Closeable {

    private final ConsumerProperties consumerProperties;
    private final KafkaConsumer<UUID, Email> consumer;
    private final EmailService emailService;

    public EmailSendConsumer(ConsumerProperties consumerProperties, EmailService emailService) {
        this.consumerProperties = consumerProperties;
        this.consumer = new KafkaConsumer<>(this.consumerProperties.buildProperties());
        this.emailService = emailService;
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
                    this.emailService.sendEmail(record.value());
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
