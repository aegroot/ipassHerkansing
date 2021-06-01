package com.example.demo.presentation.dto;

import java.util.Date;

public class FindMailDTO {
    int id;
    String sender;
    String recipient;
    String message;
    String title;
    Date date;

    public FindMailDTO(String sender, String recipient, String message, String title, Date date,int id) {
        this.id=id;
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.title = title;
        this.date = date;
    }
}
