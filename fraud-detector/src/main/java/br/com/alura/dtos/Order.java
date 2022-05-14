package br.com.alura.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {
    
    private UUID id;
    private UUID userId;
    private BigDecimal value;

    private Order() {
    }

    public Order(UUID id, UUID userId, BigDecimal value) {
        this();
        this.id = id;
        this.userId = userId;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public BigDecimal getValue() {
        return value;
    }
}
