package net.webapp.ebank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.webapp.ebank.dao.RoleRepository;
import net.webapp.ebank.dao.UserRepository;
import net.webapp.ebank.entities.Etudiant;
import net.webapp.ebank.entities.Role;
import net.webapp.ebank.entities.User;

@RestController
public class UserRestService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Secured(value={"ROLE_ADMIN", "ROLE_SCOLARITE", "ROLE_PROF", "ROLE_ETUDIANT"})
	
	@RequestMapping(value="/addUser")
	public User saveUser(User u) {
		return userRepository.save(u);
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/findUsers")
	public Page<User> listEtudiants(int page, int size) {
		return userRepository.findAll(new PageRequest(page, size));
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/addRole")
	public Role saveRole(Role r) {
		return roleRepository.save(r);
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/findRoles")
	public Page<Role> listRoles(int page, int size) {
		return roleRepository.findAll(new PageRequest(page, size));
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/addRoleToUser")
	public User addRoleToUser(String username, String role) {
		User u = userRepository.findOne(username);
		Role r= roleRepository.findOne(role);
		u.getRoles().add(r);
		userRepository.save(u);
		return u;
	}
	
	
}
