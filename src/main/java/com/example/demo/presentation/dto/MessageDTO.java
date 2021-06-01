package com.example.demo.presentation.dto;

import java.sql.Date;

public class MessageDTO {
    private String title;
    private String sender;
    private Date sendDate;

    public MessageDTO(String title, String sender, Date sendDate) {
        this.title = title;
        this.sender = sender;
        this.sendDate = sendDate;
    }

    public String getTitle() {
        return title;
    }

    public String getSender() {
        return sender;
    }

    public Date getSendDate() {
        return sendDate;
    }
}
