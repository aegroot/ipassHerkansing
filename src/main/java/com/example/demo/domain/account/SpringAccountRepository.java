package com.example.demo.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpringAccountRepository extends JpaRepository<MailAccount,Long> {
    @Query(nativeQuery = true, value = "select id,birth_date,mail,name,password from mail_account where mail=?")
    Optional<MailAccount> findMailAccountByMail(String mail);
}
