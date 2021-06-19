package com.example.demo.domain.account;

import com.example.demo.domain.message.Message;
import com.example.demo.security.data.Role;
import com.example.demo.security.data.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class MailAccount extends User {
    private Date birthDate;
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


    public MailAccount(String mail, String password, Date birthDate, String name, String lastname, Role role) {
        super(mail,password,name,lastname,role);
        this.birthDate = birthDate;
        this.recieved = new ArrayList<>();
        this.sent = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
    }
    public boolean addToBlocked(MailAccount account){
        if (account.getUsername().equals(this.getUsername()) ||this.inBlocked(account)){return  false;}
        return blocked.add(account);
    }
    public boolean removeFromBlocked(MailAccount account){
        for(MailAccount mailAccount:blocked){
            if (mailAccount.getUsername().equals(account.getUsername())){
                return blocked.remove(mailAccount);
            }
        }
        return  false;
    }


    public boolean inBlocked(MailAccount account){
        return blocked.contains(account);
    }

    public boolean addToFriends(MailAccount account){
        if (account.getUsername().equals(this.getUsername()) ||this.inBlocked(account)||inFriends(account)){return  false;}
        else
        return  friends.add(account);
    }

    public boolean removeFromFriends(MailAccount account){
        for(MailAccount mailAccount:friends){
            if (mailAccount.getUsername().equals(account.getUsername())){
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





    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
