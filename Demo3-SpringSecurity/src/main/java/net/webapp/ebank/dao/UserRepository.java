package net.webapp.ebank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.webapp.ebank.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
