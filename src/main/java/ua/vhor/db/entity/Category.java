package ua.vhor.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "es_category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7724468243394749353L;

	@Id
	@GeneratedValue
	@Column(name = "es_ctgr_id")
	private int id;
	@Column(name = "es_ctgr_name")
	private String name;
	@Column(name = "es_ctgr_description")
	private String description;
	@Column(name = "es_ctgr_available")
	private int available;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

}
