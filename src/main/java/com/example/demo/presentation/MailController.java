package com.example.demo.presentation;


import com.example.demo.application.AccountService;
import com.example.demo.application.MessageService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.domain.message.Message;
import com.example.demo.presentation.dto.FindMailDTO;
import com.example.demo.presentation.dto.MessageDTO;
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
        MailAccount sender=accountservice.findByMail(dto.getSender());
        MailAccount recipient=accountservice.findByMail(dto.getRecipient());
        return messageService.save(new Message(recipient,sender,dto.getMessage(),dto.getTitle()));
    }

    @GetMapping("{id}")
    public List<MessageDTO> findAll(@PathVariable("id") long id){
        System.out.println(id);
        List<MessageDTO>messageList=new ArrayList<>();

        List<Message> messages= accountservice.findById(id).getRecieved();
        for(Message message : messages){
            messageList.add(new MessageDTO(message.getTitle()
                    ,message.getSender().getMail()
                    ,message.getSendDate()));
        }
        return messageList;
    }
    @GetMapping("{id}/{mid}")
    public FindMailDTO findMail(@PathVariable("id") long id, @PathVariable("mid") long mid){
        Message message=messageService.findById(mid);
        if(id== message.getSender().getId()){
            return  null;
        }

        return  new FindMailDTO(message.getSender().getMail(),
                message.getRecipient().getMail(),
                message.getMessage(), message.getTitle(),message.getSendDate(), message.getId());




    }

}
