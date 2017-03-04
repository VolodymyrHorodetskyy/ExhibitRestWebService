package ua.vhor.entity;

import java.io.Serializable;

public class Criteria implements Serializable {

	private int page;
	private double minPrice;
	private double maxPrice;
	private String searchName;

	public Criteria() {
		
	}
	
	
	
	public Criteria(double minPrice, double maxPrice) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}



	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}
