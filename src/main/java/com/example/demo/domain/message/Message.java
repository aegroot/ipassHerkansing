package com.example.demo.domain.message;

import com.example.demo.domain.account.MailAccount;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Message {
    @Id
    private int id;
    @ManyToMany
    private List<MailAccount> recipient;
    @ManyToOne
    private  MailAccount sender;
    private Date sendDate;

    public Date getSendDate() {
        return sendDate;
    }

    public int getId() {
        return id;
    }

    public List<MailAccount> getRecipient() {
        return recipient;
    }

    public MailAccount getSender() {
        return sender;
    }
}
