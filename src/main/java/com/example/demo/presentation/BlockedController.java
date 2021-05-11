package com.example.demo.presentation;

import com.example.demo.application.AccountService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.presentation.dto.AccountListDto;
import com.example.demo.presentation.dto.AddAccoundToListDto;
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
    public MailAccount add(@RequestBody AddAccoundToListDto dto){
        MailAccount accountt=accountservice.findById(dto.getTarget());
        MailAccount accounta=accountservice.findById(dto.getAdder());
        accounta.addToBlocked(accountt);
        return accountservice.update(accounta);
    }
    @GetMapping
    public List<AccountListDto>getList(@RequestBody long id){
        MailAccount account=accountservice.findById(id);
        List<AccountListDto>accountListDtos=new ArrayList<>();
        for(MailAccount account1:account.getBlocked()){
            accountListDtos.add(new AccountListDto(account.getMail(),account1.getName()));
        }
        return  accountListDtos;
    }





}
