package br.com.alura.loaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;

import br.com.alura.properties.ProducerProperties;

public class ProducerPropertiesLoader {

    public static ProducerProperties load(String jsonPropertiesPath) throws IOException {
        var content = Files.readString(Path.of(jsonPropertiesPath));
        var gson = new Gson();
        var producerProperties = gson.fromJson(content, ProducerProperties.class);
        return producerProperties;
    }
}
