package br.com.alura.services;

import java.util.logging.Logger;

import br.com.alura.dtos.Email;

public class EmailService {
    
    public void sendEmail(Email email) {
        Logger.getGlobal().info("Sending email: " + email.getId());
    }
}
