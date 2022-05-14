package br.com.alura;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import br.com.alura.loaders.ProducerPropertiesLoader;
import br.com.alura.producers.EmailProducer;
import br.com.alura.producers.OrderProducer;
import br.com.alura.properties.ProducerProperties;

public class App {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        var orderProducerProperties = ProducerPropertiesLoader.load(
                "order-producer/src/main/resources/order.producer.properties.json");
        var emailProducerProperties = ProducerPropertiesLoader.load(
                "order-producer/src/main/resources/email.producer.properties.json");
        publishOrder(orderProducerProperties);
        publishEmail(emailProducerProperties);
    }

    private static void publishOrder(ProducerProperties producerProperties)
            throws InterruptedException, ExecutionException {
        try (var orderProducer = new OrderProducer(producerProperties)) {
            var orderId = UUID.randomUUID();
            var userId = UUID.randomUUID();
            var value = new BigDecimal("250.0");
            orderProducer.produce(orderId, userId, value);
        }
    }

    private static void publishEmail(ProducerProperties producerProperties)
            throws InterruptedException, ExecutionException {
        try (var emailProducer = new EmailProducer(producerProperties)) {
            var emailId = UUID.randomUUID();
            var address = "pedrobsantos98@outlook.com";
            var content = "Thank you for your order, we are processing your order.";
            emailProducer.produce(emailId, address, content);
        }
    }
}
