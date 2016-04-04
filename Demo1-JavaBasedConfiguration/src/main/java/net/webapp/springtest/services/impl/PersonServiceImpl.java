package net.webapp.springtest.services.impl;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.webapp.exceptions.PersonNotFound;
import net.webapp.springtest.models.Person;
import net.webapp.springtest.repository.PersonRepository;
import net.webapp.springtest.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);
	
//	@Autowired
	@Resource
	private PersonRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public Person findByLastname(String lastName) {
	    return repository.findByLastname(lastName);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Person> findAll() {
		return repository.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Person findById(Long Id) {
		return repository.findOne(Id);
	}
	
	@Override
	@Transactional
	public Person create(Person p) {
		return repository.save(p);
	}
	
	
	@Override
	@Transactional(rollbackFor=PersonNotFound.class)
	public Person delete(Long Id) throws PersonNotFound {
		Person personToDeleted  = repository.findOne(Id);
		if (personToDeleted == null) throw new PersonNotFound();
		repository.delete(personToDeleted);
		return personToDeleted;
	}

	/**
	 * modifies entities by first fetching them from the database, modifying some fields, 
	 * and then persisting the entities again. 
	 */
	@Override
	@Transactional(rollbackFor=PersonNotFound.class)
	public Person update(Person p) throws PersonNotFound { 
		Person personToUpdate = repository.findOne(p.getId());
		if (personToUpdate == null) throw new PersonNotFound();
		return repository.save(personToUpdate);
	}
	
	
}
