package net.webapp.ebank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.webapp.ebank.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
