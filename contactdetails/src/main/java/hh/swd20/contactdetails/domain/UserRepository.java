package hh.swd20.contactdetails.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);  // tämä on oltava!! etsitään käyttäjätietoja käyttäjätunnuksella

}
