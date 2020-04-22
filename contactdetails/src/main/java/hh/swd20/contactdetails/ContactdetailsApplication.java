package hh.swd20.contactdetails;

//import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.contactdetails.domain.ContactType;
import hh.swd20.contactdetails.domain.ContactTypeRepository;
import hh.swd20.contactdetails.domain.Person;
import hh.swd20.contactdetails.domain.PersonRepository;
import hh.swd20.contactdetails.domain.User;
import hh.swd20.contactdetails.domain.UserRepository;

@SpringBootApplication	//tämä annotaatio mahdollistaa automaattisen määrityksen(configurointi)
public class ContactdetailsApplication {

	private static final Logger log = LoggerFactory.getLogger(ContactdetailsApplication.class); // uusi loggeriattribuutti
	
	public static void main(String[] args) {
		SpringApplication.run(ContactdetailsApplication.class, args);
	}
	
//  testidatan luonti H2-testitietokantaan aina sovelluksen käynnistyessä
	@Bean
	public CommandLineRunner personDemo(PersonRepository prepository, ContactTypeRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of contactType");
			//String name ja List<Person> persons
			ContactType contactType1 = new ContactType("family");
			ContactType contactType2 = new ContactType("work");
			ContactType contactType3 = new ContactType("friend");
			ContactType contactType4 = new ContactType("relative");
			
			crepository.save(contactType1);
			crepository.save(contactType2);
			crepository.save(contactType3);
			crepository.save(contactType4);
			
			log.info("fetch all contactTypes");
			for (ContactType contactType : crepository.findAll()) {
				log.info(contactType.toString());
			}
			
			log.info("save a couple of persons");
			//String firstname, String lastname, String phoneNumber, ContactType contactType
			Person person1 = new Person("Maija", "Meikäläinen", "0400378555", contactType1); //HERJAA puhelinnumeroa, oli määritelty int:ksi?!
			Person person2 = new Person("Matti", "Meikeläinen", "0501234111", contactType2);
			Person person3 = new Person("Mikki", "Hiiri", "0404321555", contactType3);
			
			prepository.save(person1);
			prepository.save(person2);
			prepository.save(person3);
			
			log.info("fetch all persons");
			for (Person person : prepository.findAll()) {
				log.info(person.toString());
			}
			
			log.info("save a couple of users");
			// Create users: admin/admin user/user oliot (username, password, rooli)
			// käytä BCrypt Calculator => saa kryptatun salasanan, tämä kerrottuna WebSecurityConfigissa
			User user1 = new User("user1", "$2a$10$P5GfjyzLHFdYoUB2dxh1vusUz6HkIooF.VWVEPykqcuf3DaPr1RBy", "USER");
			User user2 = new User("user2", "$2a$10$7eBDsM8ke6GBNRq1bcarD.8hv.csUhbEoaiXOedw8lJ6LY8RmiQ16", "USER");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all users");
			for (User user : urepository.findAll()) {
				log.info(user.toString());
			}
		};
	}
	}

