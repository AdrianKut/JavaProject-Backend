package com.DTeam.eshop.utilities;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}