package com.george.userservice;

import com.george.userservice.model.User;
import com.george.userservice.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class UserserviceApplication {


    @Autowired
    private IUserRepository repository;

    @Bean
    public void initUsers() {
        List<User> users = Stream.of(
                new User(10L, "Ion George", "Ion", "12345"),
                new User(11L, "Andrei Coman", "Andrei", "54321")
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }


    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

}



