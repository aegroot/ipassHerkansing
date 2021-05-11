package com.example.demo.domain.account;

import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MailAccountTest {
    static MailAccount account1=new MailAccount("tomspijker@live.nl","bes5r0ng",new Date(1998, 2,20),"tom");
    static com.example.demo.domain.account.MailAccount account2=new MailAccount("marieke_doorn@live.nl","b3stday45ver",new Date(1989,4,9),"marieke");
    static MailAccount account3=new MailAccount("klaas-vlaanderen@live.nl","7rfouvi",new Date(2000,8,28),"klaas");


    @ParameterizedTest
    @MethodSource("ab")
    void addToBlocked(MailAccount a1, MailAccount a2, boolean result) {
        assertEquals(result,a1.addToBlocked(a2));
    }
    static Stream<Arguments>ab(){
        return Stream.of(
                Arguments.of(account1,account2,true),
                Arguments.of(account1,account1,false),
                Arguments.of(account2,account2,false),
                Arguments.of(account2,account1,true)
        );
    }
    @Test
    void addToBlockedAlreadyin(){
        account1.addToBlocked(account2);
        assertFalse(account1.addToBlocked(account2));
    }

    @ParameterizedTest
    @MethodSource("rfb")
    public void removeFromBlocked(com.example.demo.domain.account.MailAccount account, List<MailAccount> accounts, com.example.demo.domain.account.MailAccount rmaccount, boolean result){
        for(MailAccount accountTest :accounts){
            account.addToBlocked(accountTest);
        }
        assertEquals(result,account.removeFromBlocked(rmaccount));
    }
    static Stream<Arguments>rfb(){
        return Stream.of(
                Arguments.of(account1,List.of(account2,account3),account1,false),
                Arguments.of(account2,List.of(account1,account3),account1,true),
                Arguments.of(account2,List.of(account1),account1,true),
                Arguments.of(account2,List.of(account3),account1,false),
                Arguments.of(account2,List.of(account3,account2,account1),account2,false),
                Arguments.of(account3,List.of(account3),account1,false)
        );
    }







}
