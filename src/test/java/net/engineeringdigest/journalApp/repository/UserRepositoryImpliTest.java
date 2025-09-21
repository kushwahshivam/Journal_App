package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.service.UserArgumentProvider;
import net.engineeringdigest.journalApp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryImpliTest {


    @Autowired
    private UserRepositoryImpli userRepositoryImpli;

    @Test
    public void testSaveNewUser() {
        Assertions.assertNotNull(userRepositoryImpli.getUserForSA());
    }
}
