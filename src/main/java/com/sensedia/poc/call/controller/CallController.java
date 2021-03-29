package com.sensedia.poc.call.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sensedia.poc.call.usecase.CallUseCase;

@Controller
public abstract class CallController {

	@Autowired
	protected CallUseCase callUseCase;

	@GetMapping("/calls")
	public abstract <T> T getAll();

	@GetMapping("/calls/{id}")
	public abstract <T> T get(Long id);

	@DeleteMapping("/calls/{id}")
	public abstract <T> T delete(Long id);

	@PostMapping("/calls")
	public abstract <T> T create(String body);

}
