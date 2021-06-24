package com.example.demo.domain.account;

import com.example.demo.domain.message.Message;
import com.example.demo.security.data.Role;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MailAccountTest {

    MailAccount account1;
  MailAccount account2;
  MailAccount account3;

    @BeforeEach
    void setup(){
       account1=new MailAccount("tomspijker@live.nl","bes5r0ng",
               new Date(1998, 2,20),"tom","spijker", Role.gebruiker);
       account2=new MailAccount("marieke_doorn@live.nl","b3stday45ver",
               new Date(1989,4,9),"marieke","doorn",Role.gebruiker);
       account3=new MailAccount("klaas-vlaanderen@live.nl","7rfouvi",
               new Date(2000,8,28),"klaas","vlaanderen",Role.gebruiker);


    }



    @Test
    void addToBlockedGoodTest(){
        account1.addToBlocked(account2);
        assertTrue(account1.addToBlocked(account3));

    }
    @Test
    void addBlockedSelfTest(){
        assertFalse(account1.addToBlocked(account1));
    }
    @Test
    void addToBlockedContains(){
        account1.addToBlocked(account2);
        assertFalse(account1.addToBlocked(account2));

    }
    @Test
    void removeFromBlockedGood(){
        account1.addToBlocked(account2);
        assertTrue(account1.removeFromBlocked(account2));

    }
    @Test
    void removeFromBlockedBad(){
        account1.addToBlocked(account2);
        assertFalse(account1.removeFromBlocked(account3));

    }


    @Test
    void addToSentGood(){
        assertTrue(account1.addTosent(new Message(account2,account1,"beste ...","groeten")));
    }
    @Test
    void removeFromSentGood(){
        Message bericht=new Message(account2,account1,"beste ...","groeten");

        account1.addTosent(bericht);
        assertTrue(account1.removeFromSent(bericht));
    }
    @Test
    void removeFromSentAbsent(){
        Message bericht=new Message(account1,account2,"beste ...","groeten");
        assertFalse(account1.removeFromSent(bericht));

    }





}
