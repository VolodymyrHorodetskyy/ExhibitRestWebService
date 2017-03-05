package ua.vhor.db.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="es_photo")
public class Photo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3224341837391212280L;
	@Id
	@GeneratedValue
	@Column(name="es_phto_id")
	private int id;
	@Column(name="es_phto_reference")
	private String reference;
	@Column(name="es_phto_description")
	private String description;
	
	public Photo() {
		
	}
	
	public Photo(String reference, String description) {
		super();
		this.reference = reference;
		this.description = description;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
