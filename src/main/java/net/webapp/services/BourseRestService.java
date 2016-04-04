package net.webapp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // permet de refresh les property à l'exécution
@RestController
public class BourseRestService {

	// inject d'une property du Service Config
	@Value("${me}")
	private String message;
	
	@RequestMapping("/tellMe")
	public String tellMe() {
		System.out.println("============================ Response from Me");
		return message;
	}
	
}
