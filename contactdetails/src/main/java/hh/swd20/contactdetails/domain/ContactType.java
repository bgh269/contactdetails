package hh.swd20.contactdetails.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class ContactType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contactId;
	private String name;
	
	// JsonBackReference on api.RESTiä varten ja koska Personluokassa on JsonManagedReference
	@JsonBackReference
	// OneToMany yhteyden luonti Person.java luokkaan
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contactType")
	private List<Person> persons;
	
	public ContactType() {
		super();
	}
	//hox poista tästä categoryId parametri
	public ContactType(String name) { 
		super();
		this.name = name;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	// HOX toString ilman listaa, muuten ikuinen looppi
	@Override
	public String toString() {
		return "ContactType [contactId=" + contactId + ", name=" + name + "]";
	}
	
}
