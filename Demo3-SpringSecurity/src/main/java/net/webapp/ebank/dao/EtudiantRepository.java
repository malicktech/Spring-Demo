package net.webapp.ebank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.webapp.ebank.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{

}
