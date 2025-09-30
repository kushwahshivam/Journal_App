package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class emailServiceTests {


    @Autowired
    emailService emailService;

    @Test
    public void emailServiceTest(){
        emailService.sendEmail("shivamkushwah97522@gmail.com","hello","hello testing java mail sender");
    }
}
