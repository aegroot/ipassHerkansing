package com.example.demo.presentation.dto;

import java.sql.Date;

public class MessageListDTO {
    private String title;
    private String sender;
    private Date sendDate;

    public MessageListDTO(String title, String sender, Date sendDate) {
        this.title = title;
        this.sender = sender;
        this.sendDate = sendDate;
    }
}
