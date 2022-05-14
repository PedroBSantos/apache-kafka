package br.com.alura.dtos;

import java.util.UUID;

public class Email {

    private UUID id;
    private String address;
    private String content;

    private Email() {
    }

    public Email(UUID id, String address, String content) {
        this();
        this.id = id;
        this.address = address;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getContent() {
        return content;
    }
}
