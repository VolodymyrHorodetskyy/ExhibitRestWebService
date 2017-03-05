package ua.vhor.db.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "es_person_infor")
public class PersonInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3872611245279181737L;

	@Id
	@GeneratedValue
	@Column(name = "es_pnif_id")
	private int id;

	// private Integer personId;
	@Column(name = "es_pnif_name")
	private String name;
	@Column(name = "es_pnif_surname")
	private String surname;
	@Column(name = "es_pnif_birth")
	private Date birth;
	@Column(name = "es_pnif_tel1")
	private String telephone1;
	@Column(name = "es_pnif_tel2")
	private String telephone2;
	@Column(name = "es_pnif_email")
	private String email;
	@Column(name = "es_pnif_about")
	private String about;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "es_pnif_pid")
	private Person person;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@JsonIgnore
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonInfo(String name, String surname, Date birth,
			String telephone1, String telephone2, String email, String about) {
		this.name = name;
		this.surname = surname;
		this.birth = birth;
		this.telephone1 = telephone1;
		this.telephone2 = telephone2;
		this.email = email;
		this.about = about;
	}

	public PersonInfo() {
	}

}
