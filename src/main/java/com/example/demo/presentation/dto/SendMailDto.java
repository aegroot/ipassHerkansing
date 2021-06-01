package com.example.demo.presentation.dto;

public class SendMailDto {
    String sender;
   String recipient;
    String message;
    String title;

    public String getTitle() {
        return title;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public SendMailDto(String sender, String recipients, String message, String title) {
        this.sender = sender;
        this.recipient = recipients;
        this.message = message;
        this.title = title;
    }
}
