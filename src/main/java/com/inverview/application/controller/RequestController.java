package com.inverview.application.controller;

import com.inverview.application.entity.UserRequest;
import com.inverview.application.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RequestController {

	private final UserService userService;

	public RequestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/getAll")
	public List<UserRequest> getAllRequests(){
		return userService.findAll();
	}

	@GetMapping("/getOne/{id}")
	public UserRequest getAllRequests(@PathVariable("id")Long id){
		return userService.findOneById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteRequest(@PathVariable("id")Long id){
		userService.deleteRequest(id);
	}

	@PostMapping("/save")
	public void saveStudent(@RequestBody UserRequest request){
		userService.createRequest(request);
	}
}
