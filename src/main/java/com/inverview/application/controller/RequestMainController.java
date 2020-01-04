package com.inverview.application.controller;

import com.inverview.application.entity.UserRequest;
import com.inverview.application.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.SortedMap;

@Controller
public class RequestMainController {

	@Resource
	private SortedMap<String, String> requestTypesOptions;

	private final UserService userService;

	public RequestMainController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping({"/", "/form"})
	public String getInput(Model model) {
		model.addAttribute("userRequest", new UserRequest());
		model.addAttribute("requestTypes", requestTypesOptions.values());
		return "RequestPage";
	}

	@PostMapping("/saveRequest")
	public String saveRequest(@ModelAttribute("userRequest") @Valid UserRequest request, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", bindingResult.hasErrors());
			model.addAttribute("requestTypes", requestTypesOptions.values());
			return "RequestPage";
		} else {
			userService.createRequest(request);
			return "redirect:/getAll";
		}
	}

	@RequestMapping("/getAll")
	public String getRequests(Model model) {
		model.addAttribute("savedRequests", userService.findAll());
		return "RequestsList";
	}
}
