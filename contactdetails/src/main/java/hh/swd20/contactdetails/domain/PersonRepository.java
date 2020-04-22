package hh.swd20.contactdetails.domain;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long>{
	// CrudRepository-rajapinnan parametrisoinnissa annetaan Entity-luokan nimi: 
	// tässä Person ja toisena parametrina pääavainsarakkeen luokkatietotyyppi, tässä Long
	// BookRepository periytyy(extends) CrudRepositorystä ja perii mm. metodiesittelyt
	// findAll(), findById(), save(), deleteById()
	//List<Person> findByOwner(User owner);
	 List<Person>findByLastname(String lastname);
	 Person findByFirstname(String firstname);
}
