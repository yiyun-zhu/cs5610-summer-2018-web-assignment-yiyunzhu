package com.example.webdev2018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev2018.models.User;

public interface UserRepository
	extends CrudRepository<User, Integer> {

}
