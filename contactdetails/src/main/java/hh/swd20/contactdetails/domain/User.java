package hh.swd20.contactdetails.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="kayttaja")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //IDENTITY taulukohtainen arvo
	@Column(name="id", nullable=false, updatable=false)
	private Long userId;
	
	// Uniikki käyttäjätunnus
	@Size(min = 4, max = 20)
	@Column(name="username", nullable=false, unique=true)
	private String username;
	
	@Column(name="password", nullable=false)
	private String passwordHash;	//kryptattu salasana, Hash springboot alustalta
	
	@Column(name="role", nullable=false)
	private String role;
	
/*	TÄSSÄ YRITYS YHDISTÄÄ HLÖ-TAULU KÄYTTÄJÄTAULUUN (User owner)
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Person> persons;
*/

	public User() {
		super();
	}

	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
/*
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
*/
	//toString HOX! ilman listaa, muuten ikuinen looppi
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ "]";
	}
	
	
	

}
