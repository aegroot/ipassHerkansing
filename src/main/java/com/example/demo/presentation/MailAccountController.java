package com.example.demo.presentation;


import com.example.demo.application.AccountService;
import com.example.demo.domain.account.MailAccount;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("account")
public class MailAccountController {

    private final AccountService service;

    public MailAccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public MailAccount create(@RequestParam("mail")String mail,
                              @RequestParam("password")String password,
                              @RequestParam("birthdate")Date date,
                              @RequestParam("name")String name){
        return service.save(new MailAccount(mail,password,date,name));

    }

}
