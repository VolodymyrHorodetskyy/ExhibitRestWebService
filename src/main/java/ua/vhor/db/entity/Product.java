package ua.vhor.db.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "es_product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5548687260783075239L;
	@Id
	@GeneratedValue
	@Column(name = "es_pdct_id")
	private int id;
	@Column(name = "es_pdct_name")
	private String name;
	@Column(name = "es_pdct_description")
	private String description;
	@Column(name = "es_pdct_price")
	private double price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "es_pdct_main_photo")
	private Photo mainPhoto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "es_pdct_additional_photo")
	private Photo additionalPhoto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "es_pdct_additional_photo_2")
	private Photo additionalPhoto2;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "es_pdct_additional_photo_3")
	private Photo additionalPhoto3;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "es_pdct_additional_photo_4")
	private Photo additionalPhoto4;
	@Column(name = "es_pdct_available")
	private int available;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "es_pdct_category")
	private Category category;

	public Product() {

	}

	public Product(String name, String description, double price,
			Photo mainPhoto, Photo additionalPhoto, Photo additionalPhoto2,
			Photo additionalPhoto3, Photo additionalPhoto4) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.mainPhoto = mainPhoto;
		this.additionalPhoto = additionalPhoto;
		this.additionalPhoto2 = additionalPhoto2;
		this.additionalPhoto3 = additionalPhoto3;
		this.additionalPhoto4 = additionalPhoto4;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Photo getMainPhoto() {
		return mainPhoto;
	}

	public void setMainPhoto(Photo mainPhoto) {
		this.mainPhoto = mainPhoto;
	}

	public Photo getAdditionalPhoto() {
		return additionalPhoto;
	}

	public void setAdditionalPhoto(Photo additionalPhoto) {
		this.additionalPhoto = additionalPhoto;
	}

	public Photo getAdditionalPhoto2() {
		return additionalPhoto2;
	}

	public void setAdditionalPhoto2(Photo additionalPhoto2) {
		this.additionalPhoto2 = additionalPhoto2;
	}

	public Photo getAdditionalPhoto3() {
		return additionalPhoto3;
	}

	public void setAdditionalPhoto3(Photo additionalPhoto3) {
		this.additionalPhoto3 = additionalPhoto3;
	}

	public Photo getAdditionalPhoto4() {
		return additionalPhoto4;
	}

	public void setAdditionalPhoto4(Photo additionalPhoto4) {
		this.additionalPhoto4 = additionalPhoto4;
	}

}
