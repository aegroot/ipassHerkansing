package com.example.demo.presentation;


import com.example.demo.application.AccountService;
import com.example.demo.application.MessageService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.presentation.dto.RegisterDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class MailAccountController {

    private final AccountService accountservice;
    private  final MessageService messageService;

    public MailAccountController(AccountService accountservice, MessageService messageService) {
        this.accountservice = accountservice;
        this.messageService = messageService;
    }
/*
@RequestParam("mail")String mail,
                              @RequestParam("password")String password,
                              @RequestParam("birthdate")String date,
                              @RequestParam("name")String name
 */

    @PostMapping()
    public MailAccount create(
            @RequestBody RegisterDto registerDto){
        return accountservice.save((registerDto.toMail()));

    }




}
