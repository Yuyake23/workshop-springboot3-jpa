package com.udemy.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.course.entities.User;
import com.udemy.course.repositories.UserRepository;
import com.udemy.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		Optional<User> u = userRepository.findById(id);
		return u.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	public User update(Long id, User updatedUser) {
		User currentUser = userRepository.getReferenceById(id);
		updateData(currentUser, updatedUser);
		return userRepository.save(currentUser);
	}

	private void updateData(User currentUser, User updatedUser) {
		currentUser.setName(updatedUser.getEmail());
		currentUser.setEmail(updatedUser.getEmail());
		currentUser.setPhone(updatedUser.getPhone());
	}

}
