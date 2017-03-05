package ua.vhor.entity;

public class GoodsPageInfo {

	private double minPrice;
	private double maxPrice;
	private int sliderStep;
	private int generalAmountOfPages;

	public GoodsPageInfo() {
		super();
	}

	public GoodsPageInfo(double minPrice, double maxPrice, int sliderStep,
			int generalAmountOfPages) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.sliderStep = sliderStep;
		this.generalAmountOfPages = generalAmountOfPages;
	}

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

}
