package com.fitworld.fitness.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitworld.fitness.config.jwt.JwtUtil;
import com.fitworld.fitness.dto.UserLoginDto;
import com.fitworld.fitness.dto.UserRegistrationDto;
import com.fitworld.fitness.entity.UserCredentialsEntity;
import com.fitworld.fitness.entity.UserEntity;
import com.fitworld.fitness.repository.UserCredentialsRepo;
import com.fitworld.fitness.repository.UserEntityRepo;

@RestController
@RequestMapping(UserAuthController.API)
public class UserAuthController {
	
	static final String API = "/api/auth";
	
	private UserEntityRepo userEntityRepo;
	
	private UserCredentialsRepo userCredentialsRepo;
	
	private JwtUtil jwtUtil;
	
	public UserAuthController(UserEntityRepo userEntityRepo, UserCredentialsRepo userCredentialsRepo,JwtUtil jwtUtil) {
		super();
		System.out.println("UserAuthController constructor called");
		this.userEntityRepo = userEntityRepo;
		this.userCredentialsRepo = userCredentialsRepo;
		this.jwtUtil = jwtUtil;
	}


	@PostMapping("/register")
	@PreAuthorize("hasRole('sid')")
	public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto userDto) {
		Optional<UserCredentialsEntity> userCredsOptional = userCredentialsRepo.findByEmailId(userDto.getEmailId());
		if(userCredsOptional.isPresent()) {
			return ResponseEntity.ok("Email id already registered");
		}
		UserEntity newUser = new UserEntity();
		newUser.setName(userDto.getName());
		UserEntity savedUser = userEntityRepo.save(newUser);
		UserCredentialsEntity cred = new UserCredentialsEntity();
		cred.setEmailId(userDto.getEmailId());
		cred.setPassword(userDto.getPassword());
		cred.setUserId(savedUser.getId());
		userCredentialsRepo.save(cred);
		return ResponseEntity.ok(Map.of("jwt", jwtUtil.generateToken(userDto.getEmailId())+"","Message","User successfully registered"));
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto) {
		Optional<UserCredentialsEntity> userCredsOptional = userCredentialsRepo.findByEmailId(userLoginDto.getEmailId());
		if(userCredsOptional.isPresent()) {
			UserCredentialsEntity userCreds = userCredsOptional.get();
			if(userCreds.getPassword().equals(userLoginDto.getPassword())) {
				return ResponseEntity.ok(Map.of("jwt", jwtUtil.generateToken(userLoginDto.getEmailId())+"","Message","User successfully registered"));
			}else {
				return ResponseEntity.ok(Map.of("Message","Invalid password"));
			}
		}
		return ResponseEntity.ok(Map.of("Message","User not registered or invalid email used"));
	}
}
