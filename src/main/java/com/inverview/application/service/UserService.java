package com.inverview.application.service;

import com.inverview.application.entity.UserRequest;
import com.inverview.application.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserRequest findOneById(Long id) {
		return userRepository.findById(id).get();
	}

	public List<UserRequest> findAll() {
		return userRepository.findAll();
	}

	public void deleteRequest(Long id) {
		userRepository.deleteById(id);
	}

	public void createRequest(UserRequest request) {
		try {
			userRepository.save(request);
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
