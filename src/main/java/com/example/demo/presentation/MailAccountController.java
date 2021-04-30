package com.example.demo.presentation;


import com.example.demo.application.AccountService;
import com.example.demo.application.MessageService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.domain.message.Message;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("account")
public class MailAccountController {

    private final AccountService accountservice;
    private  final MessageService messageService;

    public MailAccountController(AccountService accountservice, MessageService messageService) {
        this.accountservice = accountservice;
        this.messageService = messageService;
    }


    @PutMapping("create")
    public MailAccount create(@RequestParam("mail")String mail,
                              @RequestParam("password")String password,
                              @RequestParam("birthdate")Date date,
                              @RequestParam("name")String name){
        return accountservice.save(new MailAccount(mail,password,date,name));

    }

    @PostMapping("send")
    public Message send(@RequestParam("send") Long sender,
                        @RequestParam("rec") List<Long> recipient){

        List<MailAccount>accounts=new ArrayList<>();
        for(Long id:recipient){
            accounts.add(accountservice.findById(id));
        }
        return messageService.save(new Message(accounts,accountservice.findById(sender)));
    }


}
