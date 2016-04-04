package net.webapp.springtest.services;


import java.util.List;

import net.webapp.exceptions.PersonNotFound;
import net.webapp.springtest.models.Person;

public interface PersonService {

	Person findByLastname(String lastName);
	
	List<Person> findAll();
	
	Person findById(Long Id);
	Person create(Person p);
	Person delete(Long Id) throws PersonNotFound;
	Person update(Person p) throws PersonNotFound;
}
