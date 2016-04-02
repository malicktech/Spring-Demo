package net.webapp.springtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping(value ="/login")
	public String doLogin(){
		return "login";
	}
	
	@RequestMapping(value ="/person")
	public String doPerson(){
		return "person";
	}
}
