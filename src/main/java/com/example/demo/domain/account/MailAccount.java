package com.example.demo.domain.account;

import com.example.demo.domain.message.Message;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class MailAccount {

    private String mail;
    private String password;
    private Date birthDate;
    private String name;
    @ManyToMany
    private List<Message>recieved;
    @ManyToMany
    private List<Message>sent;
    @ManyToMany
    private  List<MailAccount>friends;
    @ManyToMany
    private List<MailAccount>blocked;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public MailAccount(String mail, String password, Date birthDate, String name) {
        this.mail = mail;
        this.password = password;
        this.birthDate = birthDate;
        this.name = name;
        this.recieved = new ArrayList<>();
        this.sent = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getRecieved() {
        return recieved;
    }

    public void setRecieved(List<Message> recieved) {
        this.recieved = recieved;
    }

    public List<Message> getSent() {
        return sent;
    }

    public void setSent(List<Message> sent) {
        this.sent = sent;
    }

    public List<MailAccount> getFriends() {
        return friends;
    }

    public void setFriends(List<MailAccount> friends) {
        this.friends = friends;
    }

    public List<MailAccount> getBlocked() {
        return blocked;
    }

    public void setBlocked(List<MailAccount> blocked) {
        this.blocked = blocked;
    }
}
