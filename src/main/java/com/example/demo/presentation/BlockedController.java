package com.example.demo.presentation;

import com.example.demo.application.AccountService;
import com.example.demo.domain.account.MailAccount;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("blocked")
public class BlockedController {
    private final AccountService accountservice;

    public BlockedController(AccountService accountservice) {
        this.accountservice = accountservice;
    }

    @PostMapping("add")
    public MailAccount add(@RequestParam("target")Long target, @RequestParam("adder")Long adder){
        MailAccount accountt=accountservice.findById(target);
        MailAccount accounta=accountservice.findById(adder);
        accounta.addToBlocked(accountt);
        return accountservice.update(accounta);
    }


}
