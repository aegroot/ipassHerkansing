package com.example.demo.presentation.dto;

public class MailDto {
    private  String mail;
    private  String password;

    public MailDto(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }


    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
