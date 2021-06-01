package com.example.demo.presentation;

import com.example.demo.application.AccountService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.presentation.dto.AccountListDto;
import com.example.demo.presentation.dto.RegulateAccoundListDto;
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

    @PostMapping()
    public AccountListDto add(@RequestBody RegulateAccoundListDto dto){
        System.out.println(dto.getAdder()+" "+dto.getTarget());
        MailAccount accountt=accountservice.findByMail(dto.getTarget());
        MailAccount accounta=accountservice.findByMail(dto.getAdder());
        accounta.addToBlocked(accountt);
      accountservice.update(accounta);
        return new AccountListDto(accounta.getMail(),accounta.getName());
    }

    @GetMapping("{id}")
    public List<AccountListDto>getList(@PathVariable("id") long id){
        MailAccount account=accountservice.findById(id);
        List<AccountListDto>accountListDtos=new ArrayList<>();
        for(MailAccount account1:account.getBlocked()){
            accountListDtos.add(new AccountListDto(account.getMail(),account1.getName()));
        }
        return  accountListDtos;
    }

    @DeleteMapping
    public boolean deleteFromBlocked(@RequestBody RegulateAccoundListDto dto){
        MailAccount account=accountservice.findByMail(dto.getAdder());
        boolean result=account.removeFromBlocked(accountservice.findByMail(dto.getTarget()));
        accountservice.update(account);
        return  result;
    }





}
