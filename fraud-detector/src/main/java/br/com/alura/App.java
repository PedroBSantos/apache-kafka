package br.com.alura;

import java.io.IOException;

import br.com.alura.consumers.FraudDetectorConsumer;
import br.com.alura.loaders.ConsumerPropertiesLoader;
import br.com.alura.services.FraudDetectorService;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {
        var fraudDetectorService = new FraudDetectorService();
        var consumerProperties = ConsumerPropertiesLoader.load("fraud-detector/src/main/resources/frauddetector.consumer.properties.json");
        try (var fraudDetectorConsumer = new FraudDetectorConsumer(consumerProperties,
                fraudDetectorService)) {
            fraudDetectorConsumer.consume();
        }
    }
}
