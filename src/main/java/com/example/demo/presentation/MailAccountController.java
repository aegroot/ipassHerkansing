package com.example.demo.presentation;


import com.example.demo.application.AccountService;
import com.example.demo.application.MessageService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.presentation.dto.MailDto;
import com.example.demo.presentation.dto.RegisterDto;
import com.example.demo.security.data.User;
import com.example.demo.security.data.UserProfile;
import org.springframework.security.core.Authentication;
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

    @PostMapping()
    public MailAccount create(
            @RequestBody RegisterDto registerDto){
        return accountservice.save((registerDto.toMail()));

    }

    @DeleteMapping
    public void delete(@RequestBody MailDto dto) throws IllegalAccessException {


        System.out.println(dto);
        MailAccount account=accountservice.findByMail(dto.getMail());
        //if(dto.getPassword()!=account.getPassword()){throw new IllegalAccessException();}
        accountservice.delete(account);
    }
    @GetMapping
    public UserProfile check(Authentication authentication){

        return (UserProfile) authentication.getPrincipal();

    }





}
