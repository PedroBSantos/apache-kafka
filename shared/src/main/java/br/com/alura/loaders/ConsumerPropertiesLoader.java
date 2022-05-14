package br.com.alura.loaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;

import br.com.alura.properties.ConsumerProperties;

public class ConsumerPropertiesLoader {
    
    public static ConsumerProperties load(String jsonPropertiesPath) throws IOException {
        var content = Files.readString(Path.of(jsonPropertiesPath));
        var gson = new Gson();
        var consumerProperties = gson.fromJson(content, ConsumerProperties.class);
        return consumerProperties;
    }
}
