package ua.vhor.db.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ES_PERSON")
public class Person implements Serializable {

	public Person(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public Person() {

	}

	@Id
	@GeneratedValue
	@Column(name = "ES_PRSN_ID")
	private int id;
	@Column(name = "ES_PRSN_LOGIN")
	private String login;
	@Column(name = "ES_PRSN_PASSWORD")
	private String password;

	@OneToMany(mappedBy = "person")
	Set<PersonInfo> persons;

	/*
	 * @OneToOne(mappedBy = "person", cascade = CascadeType.ALL) private
	 * PersonInfo personInfo;
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<PersonInfo> getPersons() {
		return persons;
	}

	public void setPersons(Set<PersonInfo> persons) {
		this.persons = persons;
	}

	/*
	 * public PersonInfo getPersonInfo() { return personInfo; }
	 * 
	 * public void setPersonInfo(PersonInfo personInfo) { this.personInfo =
	 * personInfo; }
	 * 
	 * @Override public String toString() { return "Person [login=" + login +
	 * ", password=" + password + ", personInfo=" + personInfo + "]"; }
	 */

}
