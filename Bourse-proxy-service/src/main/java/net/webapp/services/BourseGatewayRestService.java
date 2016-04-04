package net.webapp.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.webapp.modeles.SocieteDto;

@RestController
public class BourseGatewayRestService {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/names")
	public Collection<SocieteDto> listSocietes(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "30") int size) {
		ParameterizedTypeReference<Resources<SocieteDto>> responseType = new ParameterizedTypeReference<Resources<SocieteDto>>() {
		};
		return restTemplate.exchange("http://societe-service/societes?pages=" + page + "size=" + size, HttpMethod.GET,
				null, responseType).getBody().getContent();
	}

}
