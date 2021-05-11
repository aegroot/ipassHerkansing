package com.example.demo.application;

import com.example.demo.domain.account.MailAccount;
import com.example.demo.domain.account.SpringAccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    private  final SpringAccountRepository repository;


    public AccountService(SpringAccountRepository repository) {
        this.repository = repository;
    }

    public MailAccount save(MailAccount mailAccount){
        return repository.save(mailAccount);
    }
    public void delete(MailAccount mailAccount){
        repository.delete(mailAccount);
    }
    public MailAccount findById(Long id){
        Optional<MailAccount> optionalMailAccount=repository.findById(id);
        return optionalMailAccount.orElseThrow();
    }


    public MailAccount update(MailAccount mailAccount){
        Optional<MailAccount> account=repository.findById(mailAccount.getId());
        if(account.isPresent()){
            MailAccount account1=account.get();
            account1.setBirthDate(mailAccount.getBirthDate());
            account1.setName(mailAccount.getName());
            account1.setBlocked(mailAccount.getBlocked());
            account1.setFriends(mailAccount.getFriends());
            account1.setRecieved(mailAccount.getRecieved());
            account1.setSent(mailAccount.getSent());
            account1.setPassword(mailAccount.getPassword());
            return repository.save(account1);

        }
        return  null;
    }

}
