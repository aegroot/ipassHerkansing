package com.example.demo.application;

import com.example.demo.domain.message.Message;
import com.example.demo.domain.message.SpringMessageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MessageService {
    private final SpringMessageRepository repository;

    public MessageService(SpringMessageRepository repository) {
        this.repository = repository;
    }
    public Message save(Message message){
        return  repository.save(message);
    }
    public void delete(Message message){
        repository.delete(message);
    }
    public Message findById(Long id){
        Optional<Message>messageOptional=repository.findById(id);
        return messageOptional.orElseThrow();
    }

}
