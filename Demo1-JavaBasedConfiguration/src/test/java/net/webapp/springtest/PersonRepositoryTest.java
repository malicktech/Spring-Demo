package net.webapp.springtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import net.webapp.springtest.models.Person;
import net.webapp.springtest.repository.PersonRepository;

//à présent, on peut utiliser quelques méthodes de CrudRepository
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personneRep;

	Person person = new Person();
	
	@Before
	public void setup() throws Exception {
		personneRep.deleteAll();
				
		person.setFirstname("Malick");
		person.setLastname("DIOP");
	}
	
	@Test
	public void testCrudRep() throws Exception {	
		// enregistrer une person
		Person savedPpersonne = personneRep.save(person);
		// nombre de personnes dans la base ?
		assertEquals(1, personneRep.count());
		assertEquals(true, personneRep.exists(savedPpersonne.getId()));
		
		for (Person personneDeLaListe : personneRep.findAll()) {
		// TODO show person list
		}
		Person personneTrouvee = personneRep.findOne(savedPpersonne.getId());
		personneRep.delete(savedPpersonne);
		assertEquals(0, personneRep.count());
	}
	
	@Test
	 public void testTriDesc(){
		   Iterable<Person> personnesTrouvees = 
				   personneRep.findAll(new Sort(Sort.Direction.DESC, "nom"));
	 }
	
	@Test
	 public void testPagination() {
		    assertEquals(10, personneRep.count());
		    Page<Person> personnes =
		       // 1ère page de résultats &amp; 3 résultats max.
		    		personneRep.findAll(new PageRequest(1, 3));
		    assertEquals(1, personnes.getNumber());
		    assertEquals(3, personnes.getSize()); // la taille de la pagination
		    assertEquals(10, personnes.getTotalElements()); //nb total d'éléments
		                                                    //récupérables
		    assertEquals(4, personnes.getTotalPages()); // nombre de pages
		    assertTrue(personnes.hasContent());
	}


}
