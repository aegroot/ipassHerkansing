package com.example.demo.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringAccountRepository extends JpaRepository<MailAccount,Long> {
}
