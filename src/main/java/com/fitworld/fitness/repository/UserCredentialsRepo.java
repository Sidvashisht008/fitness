package com.fitworld.fitness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitworld.fitness.entity.UserCredentialsEntity;

public interface UserCredentialsRepo extends JpaRepository<UserCredentialsEntity, Integer>{

	Optional<UserCredentialsEntity> findByEmailId(String emailId);

}
