package com.udemy.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.udemy.course.entities.User;
import com.udemy.course.repositories.UserRepository;
import com.udemy.course.services.exceptions.DatabaseException;
import com.udemy.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException();
		}
	}

	public User update(Long id, User updatedUser) {
		try {
			User currentUser = userRepository.getReferenceById(id);
			updateData(currentUser, updatedUser);
			return userRepository.save(currentUser);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User currentUser, User updatedUser) {
		currentUser.setName(updatedUser.getEmail());
		currentUser.setEmail(updatedUser.getEmail());
		currentUser.setPhone(updatedUser.getPhone());
	}

}
