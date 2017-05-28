package ua.vhor.entity;

import java.io.Serializable;
import java.util.List;

import ua.vhor.db.entity.Category;

public class GoodsPageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4582038185227602715L;

	private double minPrice;
	private double maxPrice;
	private Double currentMinPrice;
	private Double currentMaxPrice;
	private int sliderStep;
	private int generalAmountOfPages;
	private int currentPage;
	private List<Category> categories;
	private List<String> sortByValues;

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getCurrentMinPrice() {
		return currentMinPrice;
	}

	public void setCurrentMinPrice(Double currentMinPrice) {
		this.currentMinPrice = currentMinPrice;
	}

	public Double getCurrentMaxPrice() {
		return currentMaxPrice;
	}

	public void setCurrentMaxPrice(Double currentMaxPrice) {
		this.currentMaxPrice = currentMaxPrice;
	}

	public int getSliderStep() {
		return sliderStep;
	}

	public void setSliderStep(int sliderStep) {
		this.sliderStep = sliderStep;
	}

	public int getGeneralAmountOfPages() {
		return generalAmountOfPages;
	}

	public void setGeneralAmountOfPages(int generalAmountOfPages) {
		this.generalAmountOfPages = generalAmountOfPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<String> getSortByValues() {
		return sortByValues;
	}

	public void setSortByValues(List<String> sortByValues) {
		this.sortByValues = sortByValues;
	}

	public GoodsPageInfo() {
		super();
	}

	private GoodsPageInfo(GoodsPageInfoBuilder builder) {
		this.minPrice = builder.minPrice;
		this.maxPrice = builder.maxPrice;
		this.currentMinPrice = builder.currentMinPrice;
		this.currentMaxPrice = builder.currentMaxPrice;
		this.sliderStep = builder.sliderStep;
		this.generalAmountOfPages = builder.generalAmountOfPages;
		this.currentPage = builder.currentPage;
		this.categories = builder.categories;
		this.sortByValues = builder.sortByValues;
	}

	public static class GoodsPageInfoBuilder {
		private double minPrice;
		private double maxPrice;
		private Double currentMinPrice;
		private Double currentMaxPrice;
		private int sliderStep;
		private int generalAmountOfPages;
		private int currentPage;
		private List<Category> categories;
		private List<String> sortByValues;

		public GoodsPageInfoBuilder(double minPrice, double maxPrice, int sliderStep, int generalAmountOfPages,
				int currentPage, List<Category> categories, List<String> sortByValues) {
			this.minPrice = minPrice;
			this.maxPrice = maxPrice;
			this.sliderStep = sliderStep;
			this.generalAmountOfPages = generalAmountOfPages;
			this.currentPage = currentPage;
			this.categories = categories;
			this.sortByValues = sortByValues;
		}

		public GoodsPageInfoBuilder setCurrentMinPrice(Double currentMinPrice) {
			this.currentMinPrice = currentMinPrice;
			return this;
		}

		public GoodsPageInfoBuilder setCurrentMaxPrice(Double currentMaxPrice) {
			this.currentMaxPrice = currentMaxPrice;
			return this;
		}

		public GoodsPageInfo build() {
			return new GoodsPageInfo(this);
		}

	}

}
