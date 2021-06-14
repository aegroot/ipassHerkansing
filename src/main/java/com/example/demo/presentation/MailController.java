package com.example.demo.presentation;


import com.example.demo.application.AccountService;
import com.example.demo.application.MessageService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.domain.message.Message;
import com.example.demo.presentation.dto.FindMailDTO;
import com.example.demo.presentation.dto.MessageDTO;
import com.example.demo.presentation.dto.SendMailDto;
import com.example.demo.security.data.UserProfile;
import org.springframework.security.core.Authentication;
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







    @PostMapping()
    public Message send(@RequestBody SendMailDto dto, Authentication authentication){
        UserProfile profile= (UserProfile) authentication.getPrincipal();

        MailAccount sender=accountservice.findByMail(profile.getUsername());
        MailAccount recipient=accountservice.findByMail(dto.getRecipient());
        return messageService.save(new Message(recipient,sender,dto.getMessage(),dto.getTitle()));
    }

    @GetMapping()
    public List<FindMailDTO> findAll(Authentication authentication){
        UserProfile profile= (UserProfile) authentication.getPrincipal();
        List<FindMailDTO>messageList=new ArrayList<>();

        List<Message> messages= accountservice.findByMail(profile.getUsername()).getRecieved();
        for(Message message : messages){
            messageList.add(new FindMailDTO(message.getSender().getUsername(),
                    message.getRecipient().getUsername(),
                    message.getMessage(), message.getTitle(),message.getSendDate(), message.getId()));
        }
        return messageList;
    }
    @GetMapping("{mid}")
    public FindMailDTO findMail(Authentication authentication, @PathVariable("mid") long mid){
        UserProfile profile=(UserProfile)authentication.getPrincipal();
        long id=accountservice.findByMail(profile.getUsername()).getId();

        Message message=messageService.findById(mid);
        if(id!= message.getSender().getId()){
            return  null;
        }

        return  new FindMailDTO(message.getSender().getUsername(),
                message.getRecipient().getUsername(),
                message.getMessage(), message.getTitle(),message.getSendDate(), message.getId());
    }
    @GetMapping("/sent")
    public List<FindMailDTO>getSent(Authentication authentication){
        UserProfile profile=(UserProfile) authentication.getPrincipal();
        MailAccount account=accountservice.findByMail(profile.getUsername());
        List<FindMailDTO>messageList=new ArrayList<>();
        List<Message> messages=account.getSent();
        System.out.println(messages);

        for(Message message : messages){
            messageList.add( new FindMailDTO(message.getSender().getUsername(),
                    message.getRecipient().getUsername(),
                    message.getMessage(), message.getTitle(),message.getSendDate(), message.getId()));

        }
        return messageList;
    }

}
