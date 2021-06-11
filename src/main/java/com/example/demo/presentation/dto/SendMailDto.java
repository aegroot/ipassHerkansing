package com.example.demo.presentation.dto;

public class SendMailDto {
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

    public SendMailDto( String recipients, String message, String title) {
        this.recipient = recipients;
        this.message = message;
        this.title = title;
    }
}
