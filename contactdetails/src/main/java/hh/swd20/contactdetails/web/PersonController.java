package hh.swd20.contactdetails.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.contactdetails.domain.ContactTypeRepository;
import hh.swd20.contactdetails.domain.Person;
import hh.swd20.contactdetails.domain.PersonRepository;
import hh.swd20.contactdetails.domain.User;
import hh.swd20.contactdetails.domain.UserRepository;

@Controller
public class PersonController {
	// Spring-alusta luo sovelluksen käynnistyessä PersonRepository-rajapintaa toteuttavan luokan olion 
	// ja kytkee olion PersonController-luokasta luodun olion attribuuttiolioksi

	// injektoi hlörepo
	@Autowired //automaattisesti ulkopuolelta kytkee tietokannan käsittelyolion
	private PersonRepository prepository; //tässä kerrotaan rajapinta

	// injektoi yht.tietorepo
	@Autowired
	private ContactTypeRepository crepository;
	
	// injektoi käyttäjä
	@Autowired
	private UserRepository urepository;
	
	// Login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	/*
	@RequestMapping(value="/ownerspersonlist")
	public String findUsersPersonlist(Model model, Principal principal) {
		String username = principal.getName(); // hakee sisäänkirj. käyttäjätunnuksen
		User user = urepository.findByUsername(username);
		model.addAttribute("persons", prepository.findByOwner(user));
		return "personlist";
	}
	*/
	
	// hlölistaus
	@RequestMapping(value="/personlist", method=RequestMethod.GET)
	public String getAllPersons(Model model) {
		// haetaan tietokannasta henkilöt listaan
		List<Person> persons = (List<Person>)prepository.findAll();
		// laitetaan model-mappiin kirjalista templatea varten
		model.addAttribute("persons", persons);
		// palautetaan sopivan käyttöliittymätemplaten nimi
		return "personlist"; //personlist.html
	}

	// hlön poisto id:n avulla
	// @PathVariable("id") viittaa url:iin
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value="/deleteperson/{id}", method=RequestMethod.GET)
	public String deletePerson(@PathVariable("id")Long personId, Model model) {
		prepository.deleteById(personId);
		return "redirect:../personlist";
	}
	
	// hlön lisäys ja tyhjän hlölomakkeen luonti
	// yht.tiedon dropdown valikko
	@RequestMapping(value="/addperson")
	public String addPerson(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("contactTypes", crepository.findAll());
		return "addperson";
	}
	
	// hlön tallennus tietokantaan
	// hlölomakkeelle syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value="/saveperson", method=RequestMethod.POST)
	public String save(Person person) {
		prepository.save(person);
		return "redirect:personlist";
	}
	
	// hlön tietojen muokkaus id:n perusteella
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value="/editperson/{id}", method=RequestMethod.GET)
	public String editPerson(@PathVariable("id") Long personId, Model model) {
		model.addAttribute("person", prepository.findById(personId));
		model.addAttribute("contactTypes", crepository.findAll());
		return "editperson";
	}
	
	// REST-METODI
	// RESTful service, jolla haetaan kaikki hlöt
	//(jacksonkirjasto)@ResponseBody muuttaa (java)List<Person> (JSON)muotoon
	//pyydetään tietokannasta hlölista ja suoraan palautetaan(return)
	@RequestMapping(value="/persons", method=RequestMethod.GET)
	public @ResponseBody List<Person> personListRest() {
		return (List<Person>)prepository.findAll();
	}
	
	// REST-METODI
	// RESTful service, jolla haetaan hlö id:llä
	// Optional is a container object used to contain not-null objects. Optional object is used to represent null with absent value.
	@RequestMapping(value="/persons/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Person> findPersonRest(@PathVariable("id") Long personId) {
		return prepository.findById(personId);
	}
}
