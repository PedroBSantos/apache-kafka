package br.com.alura.deserializers;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.kafka.common.serialization.Deserializer;

import br.com.alura.properties.ConsumerProperties;

public class GsonDeserializer<T> implements Deserializer<T> {

    private Gson gson;
    private Class<T> deserializationType;

    public GsonDeserializer() {
        this.gson = new GsonBuilder().create();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        var deserializationType = String.valueOf(configs.get(ConsumerProperties.DESERIALIZATION_TYPE));
        try {
            this.deserializationType = (Class<T>) Class.forName(deserializationType);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        var json = new String(data);
        var object = this.gson.fromJson(json, this.deserializationType);
        return object;
    }
}
