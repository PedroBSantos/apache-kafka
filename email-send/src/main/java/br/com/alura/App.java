package br.com.alura;

import java.io.IOException;

import br.com.alura.consumers.EmailSendConsumer;
import br.com.alura.loaders.ConsumerPropertiesLoader;
import br.com.alura.services.EmailService;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {
        var emailService = new EmailService();
        var consumerProperties = ConsumerPropertiesLoader
                .load("email-send/src/main/resources/emailsend.consumer.properties.json");
        try (var emailSendConsumer = new EmailSendConsumer(consumerProperties,
                emailService)) {
            emailSendConsumer.consume();
        }
    }
}
