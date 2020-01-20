package com.inverview.application.service;

import com.inverview.application.entity.UserRequest;
import com.inverview.application.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

	private final UserRepository userRepository;

	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserRequest findOneById(Long id) {
		try {
			UserRequest userRequest = userRepository.findById(id).get();
			LOGGER.info(String.format("Found request with id: %d", id));
			return userRequest;
		} catch (Exception e) {
			String message = String.format("Request with id %d not found!", id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, message, e);
		}
	}

	public List<UserRequest> findAll() {
		List<UserRequest> userRequests = userRepository.findAll();

		int userRequestNumber = userRequests.size();
		switch (userRequestNumber){
			case 0:
				LOGGER.info("No requests found!");
				break;
			case 1:
				LOGGER.info("Only 1 request found!");
				break;
			default:
				LOGGER.info(String.format("Found %d requests", userRequests.size()));
		}

		return userRepository.findAll();
	}

	public void deleteRequest(Long id) {
		try {
			userRepository.deleteById(id);
			LOGGER.info(String.format("Deleted request with id: %d", id));
		} catch (Exception e) {
			String message = String.format("Request with id %d not found!", id);
			LOGGER.info(message);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, message, e);
		}
	}

	public void createRequest(UserRequest request) {
		try {
			userRepository.save(request);
			LOGGER.info("Request created!");
		} catch (ConstraintViolationException e) {
			String message = e.getConstraintViolations().iterator().next().getMessage();
			LOGGER.info("Constraint violation while creating! " + message);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message, e);
		}
	}
}
