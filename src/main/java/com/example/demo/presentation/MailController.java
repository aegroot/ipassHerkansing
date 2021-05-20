package com.example.demo.presentation;


import com.example.demo.application.AccountService;
import com.example.demo.application.MessageService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.domain.message.Message;
import com.example.demo.presentation.dto.MessageListDTO;
import com.example.demo.presentation.dto.SendMailDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("mail")
public class MailController {

    private final AccountService accountservice;
    private  final MessageService messageService;

    public MailController(AccountService accountservice, MessageService messageService) {
        this.accountservice = accountservice;
        this.messageService = messageService;


    }
     /*


             */


    @PostMapping()
    public Message send(@RequestBody SendMailDto dto){
        List<MailAccount>accounts=new ArrayList<>();
        for(String id: dto.getRecipients()){
            accounts.add(accountservice.findByMail(id));
        }
        return messageService.save(new Message(accounts,accountservice.findByMail(dto.getSender()),dto.getMessage(),dto.getTitle()));
    }
    @GetMapping("{id}")
    public List<MessageListDTO>findAll(@PathVariable("id") long id){
        System.out.println(id);
        List<MessageListDTO>messageListDTOList=new ArrayList<>();

        List<Message> messages= accountservice.findById(id).getRecieved();
        for(Message message : messages){
            messageListDTOList.add(new MessageListDTO(message.getTitle()
                    ,message.getSender().getMail()
                    ,message.getSendDate()));

        }
        return messageListDTOList;
    }

}
