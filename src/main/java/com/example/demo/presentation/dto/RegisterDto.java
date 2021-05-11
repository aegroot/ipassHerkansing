package com.example.demo.presentation.dto;

import com.example.demo.domain.account.MailAccount;

import java.sql.Date;

public class RegisterDto {
    private String mail;
    private String password;
    private  int gbyear;
    private  int gbmonth;
    private int gbday;
    private String naam;

   public MailAccount toMail(){
       return  new MailAccount(mail,password,new Date(gbyear-1900,gbmonth,gbday),naam);
   }

    public RegisterDto(String mail, String password, int gbyear, int gbmonth, int gbday, String naam) {
        this.mail = mail;
        this.password = password;
        this.gbyear = gbyear;
        this.gbmonth = gbmonth;
        this.gbday = gbday;
        this.naam = naam;
    }

    public String getMail() {
        return mail;
    }

    public String getNaam() {
        return naam;
    }

    public String getPassword() {
        return password;
    }
}
