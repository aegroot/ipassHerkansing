package com.example.demo.domain.account;

import com.example.demo.domain.message.Message;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class MailAccount{
    @Column(unique=true)
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public MailAccount() {
    }

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
    }
    public boolean addToBlocked(MailAccount account){
        if (account.getMail()==this.getMail()||this.inBlocked(account)){return  false;}
        return blocked.add(account);
    }
    public boolean removeFromBlocked(MailAccount account){
        for(MailAccount mailAccount:blocked){
            if (mailAccount.getMail() == account.getMail()){
                return blocked.remove(mailAccount);
            }
        }
        return  false;
    }


    public boolean inBlocked(MailAccount account){
        return blocked.contains(account);
    }

    public boolean addToFriends(MailAccount account){
        if (account.getMail()==this.getMail()||this.inBlocked(account)||inFriends(account)){return  false;}
        else
        return  friends.add(account);
    }

    public boolean removeFromFriends(MailAccount account){
        for(MailAccount mailAccount:friends){
            if (mailAccount.getMail() == account.getMail()){
                blocked.remove(mailAccount);
                return  true;
            }
        }
        return  false;
    }
    public boolean inFriends(MailAccount account){
        return friends.contains(account);
    }

    public boolean addTosent(Message message){
        return  sent.add(message);
    }
    public boolean removeFromSent(Message message){
        for(Message message1:sent){
            if(message1.getId()== message.getId()){
                sent.remove(message1);
                return  true;
            }
        }
        return  false;
    }
    public  boolean addToRecieved(Message message){
       return recieved.add(message);
    }
    public boolean removeFromRecieved(Message message){
        for(Message message1:recieved){
            if(message1.getId()== message.getId()){
                sent.remove(message1);
                return true;
            }
        }
        return  false;

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
