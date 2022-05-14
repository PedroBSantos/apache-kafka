package br.com.alura.services;

import java.util.logging.Logger;

import br.com.alura.dtos.Order;

public class FraudDetectorService {
    
    public void checkForFraud(Order order) {
        Logger.getGlobal().info("Checking for fraud in order: " + order.getId());
    }
}
