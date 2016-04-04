package net.webapp.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.webapp.exceptions.PersonNotFound;
import net.webapp.springtest.models.Person;
import net.webapp.springtest.services.PersonService;
import net.webapp.springtest.services.impl.PersonServiceImpl;


@Controller
//@RequestMapping(value="/Person")
public class PersonController {
	
	static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Autowired
	private PersonService personService;
	
	/*
	@RequestMapping(value={"/index", "/"}, method = RequestMethod.GET)
	public String setupForm(Map<String, Object> map){
		LOG.info("Person/: SetupForm");
		Person Person = new Person();
		map.put("Person", Person);
		map.put("PersonList", personService.findAll());
		return "Person";
	}
	*/
	
    /**
    * Request mapping for person 
    */
    @RequestMapping(value = "/personlist", method = RequestMethod.GET)
    public ModelAndView getUsers() {
        ModelAndView mv= new ModelAndView("home2");
        mv.addObject("usersList", personService.findAll());
        return mv;
    }
	
	/*
    * Rest web service
    @RequestMapping(value = "/usersList", method = RequestMethod.GET)
    public @ResponseBody List<UserDto> getUsersRest() {
        return userService.findAll();
    }
	 */
	
	/*
	 
	@RequestMapping(value="/Person.do", method=RequestMethod.POST)
	public String doActions(@ModelAttribute Person Person, BindingResult result, @RequestParam String action, Map<String, Object> map){
		Person PersonResult = new Person();
		switch(action){
		case "add":
			personService.create(Person);
			PersonResult = Person;
			break;
		case "edit":			
			try {
				personService.update(Person);
			} catch (PersonNotFound e) {
				LOG.info("Mis a jour impossible du Person {} : " + e.getMessage(), Person.getId());				
				e.printStackTrace();
			}
			PersonResult = Person;
			break;
		case "delete":
			try {
				personService.delete(Person.getId());
			} catch (PersonNotFound e) {
				e.printStackTrace();
			}
			PersonResult = new Person();
			break;
		case "search":
			Person searchedPerson = personService.findById(Person.getId());
			PersonResult = searchedPerson!=null ? searchedPerson : new Person();
			break;
		}
		map.put("Person", PersonResult);
		map.put("PersonList", personService.findAll());
		return "Person";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView newPersonPage() {
		ModelAndView mav = new ModelAndView("Person-new", "Person", new Person());
		return mav;
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView createNewPerson(@ModelAttribute Person Person, 
			final RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		String message = "New Person "+Person.getLastname()+" was successfully created.";

		personService.create(Person);
		mav.setViewName("redirect:/index.html");

		redirectAttributes.addFlashAttribute("message", message);	
		return mav;		
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView PersonListPage() {
		ModelAndView mav = new ModelAndView("Person-list");
		List<Person> PersonList = personService.findAll();
		mav.addObject("PersonList", PersonList);
		return mav;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editPersonPage(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("Person-edit");
		Person Person = personService.findById(id);
		mav.addObject("Person", Person);
		return mav;
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView editPerson(@ModelAttribute Person Person,
			@PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws PersonNotFound {

		ModelAndView mav = new ModelAndView("redirect:/index.html");
		String message = "Person was successfully updated.";

		personService.update(Person);

		redirectAttributes.addFlashAttribute("message", message);	
		return mav;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deletePerson(@PathVariable Long id,
			final RedirectAttributes redirectAttributes) throws PersonNotFound {

		ModelAndView mav = new ModelAndView("redirect:/index.html");		

		Person Person = personService.delete(id);
		String message = "The Person "+Person.getLastname()+" was successfully deleted.";

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
	*/
	
}
