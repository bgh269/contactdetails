package hh.swd20.contactdetails;

import static org.assertj.core.api.Assertions.assertThat; // tämä täytyi lisätä tänne kopioimalla demosta, eclipse ei löytänyt
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

//import org.junit.Test; ei löydä testiä tällä?!
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.contactdetails.domain.ContactType;
import hh.swd20.contactdetails.domain.ContactTypeRepository;
import hh.swd20.contactdetails.domain.Person;
import hh.swd20.contactdetails.domain.PersonRepository;
import hh.swd20.contactdetails.domain.User;
import hh.swd20.contactdetails.domain.UserRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactTypeRepositoryTest {
	
	@Autowired
	private ContactTypeRepository crepository;

	@Autowired
	private PersonRepository prepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void findByNameShouldReturnContactType() {
		List<ContactType> contactType = crepository.findByName("work");
		assertThat(contactType).hasSize(1);
		assertThat(contactType.get(0).getName()).isEqualTo("work");
	}
	@Test
	public void createNewContactType() {
		ContactType contactType = new ContactType("school");
		crepository.save(contactType);
		assertThat(contactType.getName()).isNotNull();
	}
	@Test
	public void findAllContactTypes() {
		ContactType contactType = new ContactType("high school");
		crepository.save(contactType);
		assertNotNull(crepository.findAll());
	}
	@Test
	public void testDeleteContactType() {
		ContactType contactType = new ContactType("universty");
		crepository.save(contactType);
		crepository.delete(contactType);
	}
	
	@Test
	public void findByLastNameShouldReturnPerson() {
		List<Person> person = prepository.findByLastname("Meikäläinen");
		assertThat(person).isNotNull();
		assertThat(person.get(0).getLastname()).isEqualTo("Meikäläinen");
	}
	@Test
	public void testGetPerson() {
		Person person = new Person("Pirjo", "Poikkeus", "0506789000", null);
		prepository.save(person);
		Person person2 = prepository.findByFirstname("Pirjo");
		assertNotNull(person);
		assertEquals(person2.getFirstname(), person.getFirstname());
		assertEquals(person2.getLastname(), person.getLastname());
	}
	@Test
	public void deleteByPersonIdTest() {
		Person person = new Person("Pertti", "Poikkeustapaus", "0506789111", null);
		Person pers = prepository.save(person);
		prepository.deleteById(pers.getPersonId());
	}
	@Test
	public void createNewUser() {
		User user = new User("PirjoKayttaja", "$2a$10$Ym7Izgwexp/FeKcRIhvtiuqRudgJal.zlXbYEZG3DKI6PtfmK9XSu", "USER");
		urepository.save(user);
		assertThat(user.getUsername()).isEqualTo("PirjoKayttaja");
	}
	@Test
	public void findByUsernameShouldReturnUser() {
		User user = urepository.findByUsername("user1");
		assertThat(user.getUsername()).isNotNull();
		assertThat(user.getRole()).isEqualTo("USER");
	}
	@Test
	public void testDeleteUser() {
		User user = new User("user3", "$2a$10$XgL3PO3xRu/5be7toFrqIOkpUpTpBbJj5Oi1bYCw9RKGiqBMYttDa", "USER");
		urepository.save(user);
		urepository.delete(user);
	}
}
