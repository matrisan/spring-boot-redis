package com.github.springbootredis.schedule;

import com.github.springbootredis.pojo.Person;
import org.jetbrains.annotations.Contract;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created in 16:22 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Component
public class ScheduleSendingMessagesTask {

    private final RedisTemplate<String, Person> redisTemplate;

    @Contract(pure = true)
    public ScheduleSendingMessagesTask(RedisTemplate<String, Person> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void task() {
        redisTemplate.convertAndSend("DataTest", getPerson());
        Objects.requireNonNull(redisTemplate.getClientList()).forEach(one -> System.out.println(one.getAddressPort()));
    }

    private static Person getPerson() {
        Person person = new Person();
        person.setId("test-id");
        person.setPass("test-name");
        person.setPass("test-pass");
        person.setAge(123);
        person.setExpiration(System.currentTimeMillis());
        return person;
    }

}