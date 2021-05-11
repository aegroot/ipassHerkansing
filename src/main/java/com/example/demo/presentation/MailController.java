package com.example.demo.presentation;


import com.example.demo.application.AccountService;
import com.example.demo.application.MessageService;
import com.example.demo.domain.account.MailAccount;
import com.example.demo.domain.message.Message;
import com.example.demo.presentation.dto.MessageListDTO;
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
    public Message send(@RequestParam("send") Long sender,
                        @RequestParam("rec") List<Long> recipient,
                        @RequestParam("message")String message,
                        @RequestParam("title")String title
            ){
        List<MailAccount>accounts=new ArrayList<>();
        for(Long id:recipient){
            accounts.add(accountservice.findById(id));
        }
        return messageService.save(new Message(accounts,accountservice.findById(sender),message,title));
    }
    @GetMapping("{id}")
    public List<MessageListDTO>findAll(@PathVariable("id") long id){
        List<MessageListDTO>messageListDTOList=new ArrayList<>();

        List<Message> messages= accountservice.findById( id).getRecieved();
        for(Message message : messages){
            messageListDTOList.add(new MessageListDTO(message.getTitle()
                    ,message.getSender().getMail()
                    ,message.getSendDate()));

        }
        return messageListDTOList;
    }

}
