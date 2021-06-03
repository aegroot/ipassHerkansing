package com.example.demo.presentation;

import com.example.demo.application.AccountService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.presentation.dto.AccountListDto;
import com.example.demo.presentation.dto.RegulateAccoundListDto;
import com.example.demo.security.data.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("blocked")
public class BlockedController {
    private final AccountService accountservice;

    public BlockedController(AccountService accountservice) {
        this.accountservice = accountservice;
    }

    @PostMapping("{username}")
    public AccountListDto add(@PathVariable("username") String username,Authentication authentication){
        UserProfile profile=(UserProfile)authentication.getPrincipal();
        String name=profile.getUsername();

        System.out.println(name+" "+username);
        MailAccount accountt=accountservice.findByMail(name);
        MailAccount accounta=accountservice.findByMail(username);
        accounta.addToBlocked(accountt);
      accountservice.update(accounta);
        return new AccountListDto(accounta.getUsername(),accounta.getUsername());
    }

    @GetMapping()
    public List<AccountListDto>getList( Authentication authentication){
        UserProfile profile=(UserProfile)authentication.getPrincipal();

        MailAccount account=accountservice.findByMail(profile.getUsername());
        List<AccountListDto>accountListDtos=new ArrayList<>();
        for(MailAccount account1:account.getBlocked()){
            accountListDtos.add(new AccountListDto(account.getUsername(),account1.getUsername()));
        }
        return  accountListDtos;
    }

    @DeleteMapping("{username}")
    public boolean deleteFromBlocked(@PathVariable("username")String username,Authentication authentication){
        UserProfile profile=(UserProfile) authentication.getPrincipal();
        MailAccount account=accountservice.findByMail(profile.getUsername());
        boolean result=account.removeFromBlocked(accountservice.findByMail(username));
        accountservice.update(account);
        return  result;
    }





}
