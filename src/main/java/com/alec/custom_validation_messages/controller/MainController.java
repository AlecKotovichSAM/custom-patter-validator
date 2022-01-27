package com.alec.custom_validation_messages.controller;

import javax.validation.Valid;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alec.custom_validation_messages.model.RegistrationDTO;

@RestController
@CrossOrigin()
public class MainController {

	@GetMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}

	@GetMapping({ "/locale" })
	public String locale() {
		return LocaleContextHolder.getLocale().getLanguage();
	}

	@PostMapping({ "/register" })
	public void register(@RequestBody @Valid RegistrationDTO registrationDTO) {
		System.out.println(registrationDTO.toString());
	}

}

