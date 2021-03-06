package com.example.demo.domain.message;


import com.example.demo.domain.account.MailAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String message;
    private String title;
    @ManyToOne
    @JsonIgnore
    private MailAccount recipient;
    @ManyToOne
    @JsonIgnore
    private  MailAccount sender;
    private Date sendDate;

    public Message(MailAccount recipient, MailAccount sender,String message,String title) {
        this.title=title;
        this.message=message;
        this.recipient = recipient;
        this.sender = sender;
        this.sendDate = new Date(System.currentTimeMillis());

        recipient.addToRecieved(this);
        sender.addTosent(this);
    }

    public Message() {

    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public long getId() {
        return id;
    }

    public MailAccount getRecipient() {
        return recipient;
    }

    public MailAccount getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", title='" + title + '\'' +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", sendDate=" + sendDate +
                '}';
    }
}
