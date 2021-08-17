package com.kcode.quoteapp;

import com.kcode.quoteapp.entity.User;
import com.kcode.quoteapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class QuoteappApplication {

	@Autowired
	private UserRepository userRepository;

//	@PostConstruct
//	public void initUsers() {
//        List<User> users = Stream.of(
//				new User( "user1", "user1@gmail.com", "pwd1"),
//				new User( "user2", "user2@gmail.com","pwd2" ),
//				new User( "user3", "user3@gmail.com","pwd3" )
//        ).collect(Collectors.toList());
//		userRepository.saveAll(users);
//	}


	public static void main(String[] args) {
		SpringApplication.run(QuoteappApplication.class, args);
	}

}
