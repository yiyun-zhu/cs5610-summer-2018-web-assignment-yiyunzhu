package com.example.webdev2018.sevices;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev2018.models.User;
import com.example.webdev2018.repositories.UserRepository;



@RestController
public class UserService {
	@Autowired
	UserRepository repository;
		
	// createNewUser
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	// deleteById
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	// findById
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		Optional<User> data = repository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	// update
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int id, @RequestBody User newUser) {
		Optional<User> data = repository.findById(id);
		if(data.isPresent()) {
			User user = data.get();
			user.setPassword(newUser.getPassword());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		return null;
	}

	@GetMapping("/api/user")
	public Iterable<User> findAllUsers(
			@RequestParam(name="username",
			required=false) String username,
			@RequestParam(name="password",
			required=false) String password) {
		if (username != null && password != null) {
			Iterable<User> user = repository.findUserByCredentials(username, password);
			return user;  
		} else if (username != null) {
			Iterable<User> user = repository.findUserByUsername(username);
			return user;
		} else {
			return repository.findAll();
		}
	}
	
	// register
	@PostMapping("/api/register")
	public User register(@RequestBody User newUser, HttpSession session) { //
		Iterable<User> user = findAllUsers(newUser.getUsername(), null);
		Iterator<User> itr = user.iterator();
		if (itr.hasNext()) {
			return null;
		}
		User newData = createUser(newUser);		
		session.setAttribute("user", newData);
		return newData;
	}
	
	// login
		@PostMapping("/api/login")
		public User login(@RequestBody User user, HttpSession session) { //
			Iterable<User> registedUser = findAllUsers(
					user.getUsername(), user.getPassword());
			Iterator<User> itr = registedUser.iterator();
			if (itr.hasNext()) {
				User loggedinUser = itr.next();
				session.setAttribute("user", loggedinUser);
				return (User)session.getAttribute("user");
			}
			return null;
		}
		

}
