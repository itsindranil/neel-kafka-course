package com.neel.service;

import org.springframework.stereotype.Component;

@Component
public class WelcomeServiceClass {
	public String WelcomeMessage() {
		return "Dependency Injection method using component scan -"
				+ " All the Business logic will be written here.";
	}

}
