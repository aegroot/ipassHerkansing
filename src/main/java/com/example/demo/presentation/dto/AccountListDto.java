package com.example.demo.presentation.dto;

public class AccountListDto {
    private String mail;
    private String naam;

    public AccountListDto(String mail, String naam) {
        this.mail = mail;
        this.naam = naam;
    }
    public String getMail() {
        return mail;
    }

    public String getNaam() {
        return naam;
    }
}
