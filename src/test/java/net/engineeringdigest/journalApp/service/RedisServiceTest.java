package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisServiceTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testRedisService() {
        redisTemplate.opsForValue().set("email","Shivam@gmail.com");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a = 1;

    }

}
