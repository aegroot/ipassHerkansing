package com.example.demo.presentation.dto;

import com.example.demo.domain.account.MailAccount;
import com.example.demo.security.data.Role;

import java.sql.Date;

public class RegisterDto {
    private String mail;
    private String password;
    private  int gbyear;
    private  int gbmonth;
    private int gbday;
    private String firstname;
    private String lastname;

   public MailAccount toMail(){
       return  new MailAccount(mail,password,new Date(gbyear-1900,gbmonth,gbday), firstname, lastname, Role.gebruiker);
   }


    public RegisterDto(String mail, String password, int gbyear, int gbmonth, int gbday, String firstname,String lastname) {
        this.mail = mail;
        this.password = password;
        this.gbyear = gbyear;
        this.gbmonth = gbmonth;
        this.gbday = gbday;
        this.firstname = firstname;
        this.lastname=lastname;
    }

    public String getMail() {
        return mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }

    public String getLastname() {
        return lastname;
    }
}
