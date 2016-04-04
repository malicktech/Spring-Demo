package net.webapp.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.webapp.springtest.models.User;


public interface UserRepository extends JpaRepository<User, Long>  {
	
	User findByUsername(String username);

}
