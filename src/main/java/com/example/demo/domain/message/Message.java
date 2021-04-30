package com.example.demo.domain.message;

import com.example.demo.domain.account.MailAccount;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToMany
    private List<MailAccount> recipient;
    @ManyToOne
    private  MailAccount sender;
    private Date sendDate;

    public Message( List<MailAccount> recipient, MailAccount sender) {
        this.recipient = recipient;
        this.sender = sender;
        this.sendDate = new Date(System.currentTimeMillis());

        sender.addTosent(this);
        for(MailAccount account:recipient){
            if(!account.inBlocked(sender)){
            account.addToRecieved(this);}
            else {recipient.remove(account);}
        }
    }

    public Message() {

    }

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
