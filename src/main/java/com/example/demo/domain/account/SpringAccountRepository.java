package com.example.demo.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpringAccountRepository extends JpaRepository<MailAccount,Long> {
    @Query(nativeQuery = true, value = "select * from users where username=?")
    Optional<MailAccount> findMailAccountByMail(String mail);


}
