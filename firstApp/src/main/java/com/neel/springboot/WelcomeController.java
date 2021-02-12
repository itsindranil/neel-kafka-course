package com.neel.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neel.service.WelcomeServiceClass;

@RestController
public class WelcomeController {
	//We inject the dependency created below by Auto Wiring
	@Autowired
	//private welcomeServiceClass msg = new welcomeServiceClass();
	private WelcomeServiceClass msg;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return msg.WelcomeMessage();
	}
}
