package com.fitworld.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitworld.fitness.entity.UserEntity;

public interface UserEntityRepo extends JpaRepository<UserEntity, Integer>{

}
