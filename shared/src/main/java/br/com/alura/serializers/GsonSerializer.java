package br.com.alura.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.kafka.common.serialization.Serializer;

public class GsonSerializer<T> implements Serializer<T> {

    private Gson gson;

    public GsonSerializer() {
        this.gson = new GsonBuilder().create();
    }

    @Override
    public byte[] serialize(String topic, T data) {
        var json = this.gson.toJson(data);
        return json.getBytes();
    }
}
