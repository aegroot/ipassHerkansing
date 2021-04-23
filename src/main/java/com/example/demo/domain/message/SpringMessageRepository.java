package com.example.demo.domain.message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringMessageRepository extends JpaRepository<Message,Long> {

}
