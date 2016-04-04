package net.webapp.springtest.repository;

/**
 * 
 * JpaRepository : extend repository, CRUDRepository and PagingAndSortingRepository
 * génère l’implémentation des méthodes pour nous.
 * fournit les méthodes de base pour créer, lire, mettre-à-jour et supprimer des objets ou des ensembles d'objets.
 * 
 *  Spring-Data-JPA analyses the method name and generates a query using the JPA criteria.
 */
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.webapp.springtest.models.Person;

// J
public interface PersonRepository extends JpaRepository<Person, Long> {

	/*
	 * common méthod CRUD generated : (save, findOne, findAll, pagination, etc.). génération automatique
	 */

	// recherche une personne par son attribut "lastname"
	Person findByLastname(String lastname);

	/* Query creation from method names */

	List<Person> findByEmailAddressAndLastname(String emailAddress, String lastname);

	// Enables the distinct flag for the query
	List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
	List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);
	
	/* Enabling ignoring case for an individual property */
	
	List<Person> findByLastnameIgnoreCase(String lastname);
	List<Person> findByLastnameLike(String lastname);	
	
	/* Enabling ignoring case for all suitable properties */
	List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);
	Person findByLastnameOrFirstname(String lastname, String firstname);
	
	/* Enabling static ORDER BY for a query */
	List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
	List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
	  
	// Renvoie une liste d'utilisateurs triée selon le paramètre "sort"
	List<Person> findByLastname(String lastname, Sort sort);
	
	/* Using Pageable, Slice and Sort in query method */
	
	// Renvoie un résultat paginé avec des méta-données sur la recherche (nombre de pages, etc.)
	//	Page<Person> findByLastname(String lastname, Pageable pageable);
	// Renvoie une liste d'utilisateurs qui prend en compte les paramètres de pagination données en paramètre
	//	List<Person> findByLastname(String lastname, Pageable pageable);
	// List<User> findByLastname(String lastname, Sort sort);

	/* Limiting query results --------------------------------- */
	
	Person findFirstByOrderByLastnameAsc();
	// Person findTopByOrderByAgeDesc();
	Page<Person> queryFirst10ByLastname(String lastname, Pageable pageable);
	Slice<Person> findTop3ByLastname(String lastname, Pageable pageable);
	
	// ??? page and slice 
	
	/* Streaming query results --------------------------------- */
	
	@Query("select u from User u")
	Stream<Person> findAllByCustomQueryAndStream();
	
	Stream<Person> readAllByFirstnameNotNull();
	
	/* TODO TO TEST --------------------------------- */
	Long deleteByLastname(String lastname);

	List<Person> removeByLastname(String lastname);

	// JPA
	/*
	@Query("from Personne p where p.lastname = ?1 and p.firstname = ?2")
	Person maRequêteAvecQueryDeRechercheParNomEtPrenom(String lastname, String firstname);
	 */
	
	/*
	@Query("update Personne p set p.lastname = :lastname where p.id = :id")
	@Modifying
	int metAJourNom(@Param("lastname") String lastname, @Param("id") Long id);
	*/
	
	// custom query is useful when you want to update just some fields in database
	@Modifying(clearAutomatically = true)
	@Query("update Person p set p.firstname = ?1, p.lastname = ?2 where p.id = ?3")
	void setPersonInfoById(String firstname, String lastname, Integer userId);
	
	// 	modify adress

	// TODO to test

	// update

	// suppression en lots des entités (deleteInBatch()), le vidage du cache (flush()), etc.

	// @Query(nativeQuery="") écrire des requêtes en SQL directement.
	
	// ??????  check property espression 

}
