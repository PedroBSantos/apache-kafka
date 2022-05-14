package br.com.alura.properties;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;

public class ProducerProperties {

    private String topic;
    private String bootstrapServer;
    private String keySerializer;
    private String valueSerializer;

    private ProducerProperties() {
    }

    public ProducerProperties(String topic, String bootstrapServer, String keySerializer, String valueSerializer) {
        this();
        this.topic = topic;
        this.bootstrapServer = bootstrapServer;
        this.keySerializer = keySerializer;
        this.valueSerializer = valueSerializer;
    }

    public String getBootstrapServer() {
        return bootstrapServer;
    }

    public String getTopic() {
        return topic;
    }

    public String getKeySerializer() {
        return keySerializer;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public Properties buildProperties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, this.keySerializer);
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, this.valueSerializer);
        return properties;
    }
}
