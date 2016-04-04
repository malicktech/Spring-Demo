package net.webapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import net.webapp.entities.Societe;

@RepositoryRestResource
public interface SocieteRepository  extends JpaRepository<Societe, Long>{

	/**
	 * consulter societe par mot clé
	 * @param key	mot clé à recherché
	 * 
	 * e.g : get by AF key : 
	 * http://localhost:8080/societes/search/societesByKey?key=%25AF%25&page=0&size=2
	 * @return
	 */
	@Query("select s from Societe  s where s.nom like :key")
	public Page<Societe> societesByKey(@Param("key") String key, Pageable pageable);
	
}
