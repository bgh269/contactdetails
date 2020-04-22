package hh.swd20.contactdetails.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long personId;
	private String firstname;
	private String lastname;
	private String phoneNumber;
	
	/*
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "userId")
	private User owner; //tiedon luoja (Userluokan omistajaolio)
	*/
	
	@ManyToOne
	@JsonManagedReference // JsonManagedReference on api.RESTiä varten, ettei tule ikuista looppia
	@JoinColumn(name = "contactId") //@JoinColumn määrittää suhteen omistajan, yhdistää rivin, jonka nimi on contactId ContactType luokassa
	private ContactType contactType;

	public Person() {
		super();
	}

	public Person(String firstname, String lastname, String phoneNumber,ContactType contactType) { // User owner,
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		//this.owner = owner;
		this.contactType = contactType;
	}


	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
/*
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
*/
	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}
/*
	@Override
	public String toString() {
		if (this.contactType != null && this.owner != null)
			return "Person [personId=" + personId + ", firstname=" + firstname + ", lastname=" + lastname + ", phoneNumber="
				+ phoneNumber + ", owner=" + this.getOwner() + ", contactType=" + this.getContactType() + "]";
		else
			return "Person [personId=" + personId + ", firstname=" + firstname + ", lastname=" + lastname + ", phoneNumber="
			+ phoneNumber + "]";
	}

*/	
	@Override
	public String toString() {
		if (this.contactType != null) //+ " department =" + this.getDepartment() + "]";	
			return "Person [personId=" + personId + ", firstname=" + firstname + ", lastname=" + lastname
					+ ", phoneNumber=" + phoneNumber + " contactType =" + this.getContactType() + "]";
		else
			return "Person [personId=" + personId + ", firstname=" + firstname + ", lastname=" + lastname
					+ ", phoneNumber=" + phoneNumber + "]";
	}

}
