package net.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import net.webapp.entities.Societe;

@Configuration
public class AppConfig extends RepositoryRestMvcConfiguration {


	@Override
	protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		System.err.println("----------- configureRepositoryRestConfiguration ");
		// add id on otput rest response 
		config.exposeIdsFor(Societe.class);
	}
}
