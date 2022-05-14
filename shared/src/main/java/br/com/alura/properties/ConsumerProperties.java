package br.com.alura.properties;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;

public class ConsumerProperties {
    
    private String topic;
    private String bootstrapServer;
    private String keyDeserializer;
    private String valueDeserializer;
    private String groupId;
    private String maxPollConfig;
    private String autoOffsetConfig;
    private long pollDuration;
    private String deserializationType;

    public static String DESERIALIZATION_TYPE = "br.com.alura.ecommerce.deserialization_type";

    public ConsumerProperties(String topic, String bootstrapServer, String keyDeserializer, String valueDeserializer,
            String groupId, String maxPollConfig, String autoOffsetConfig, long pollDuration,
            String deserializationType) {
        this.topic = topic;
        this.bootstrapServer = bootstrapServer;
        this.keyDeserializer = keyDeserializer;
        this.valueDeserializer = valueDeserializer;
        this.groupId = groupId;
        this.maxPollConfig = maxPollConfig;
        this.autoOffsetConfig = autoOffsetConfig;
        this.pollDuration = pollDuration;
        this.deserializationType = deserializationType;
    }

    public String getAutoOffsetConfig() {
        return autoOffsetConfig;
    }

    public String getBootstrapServer() {
        return bootstrapServer;
    }

    public String getDeserializationType() {
        return deserializationType;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public String getMaxPollConfig() {
        return maxPollConfig;
    }

    public long getPollDuration() {
        return pollDuration;
    }

    public String getTopic() {
        return topic;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public Properties buildProperties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServer);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.autoOffsetConfig);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, this.keyDeserializer);
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, this.valueDeserializer);
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, this.maxPollConfig);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, this.groupId);
        properties.setProperty(ConsumerProperties.DESERIALIZATION_TYPE, this.deserializationType);
        return properties;
    }
}
