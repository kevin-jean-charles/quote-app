package com.kcode.quote.app.service;

import com.kcode.quote.app.model.UserDao;
import com.kcode.quote.app.model.UserDto;
import com.kcode.quote.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		UserDao user = userRepository.findByUsername(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}

		Optional<UserDao> userOptional = userRepository.findByUsername(username);
		if (userOptional.isEmpty()){
			userOptional.orElseThrow(() -> new UsernameNotFoundException("Quote by id : " + username + " not found"));
		}

		return new org.springframework.security.core.userdetails.User(
				userOptional.get().getUsername(),
				userOptional.get().getPassword(),
				new ArrayList<>()) ;
	}

	public UserDao save(UserDto user) {
		UserDao newUser = new UserDao();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}


}