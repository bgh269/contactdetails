package hh.swd20.contactdetails.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContactTypeRepository extends CrudRepository<ContactType, Long> {

	List<ContactType> findByName(String name);
}
