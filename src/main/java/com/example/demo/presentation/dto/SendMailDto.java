package com.example.demo.presentation.dto;

import java.util.List;

public class SendMailDto {
    String sender;
    List<String> recipients;
    String message;
    String title;

    public String getTitle() {
        return title;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public SendMailDto(String sender, List<String> recipients, String message, String title) {
        this.sender = sender;
        this.recipients = recipients;
        this.message = message;
        this.title = title;
    }
}
