package ua.vhor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ua.vhor.db.entity.Category;
import ua.vhor.db.entity.Product;
import ua.vhor.entity.Criteria;
import ua.vhor.entity.GoodsPageInfo;
import ua.vhor.helpers.OrderByHelper;
import ua.vhor.helpers.PaginationHelper;
import ua.vhor.repository.CategoryRepository;
import ua.vhor.repository.ProductRepository;
import ua.vhor.specifications.MinMaxPriceSpecification;

@Service
public class GoodsPageService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	private final int sliderStep = 1;
	private final double defaultMinPrice = 0;
	private final double defaultMaxPrice = 0;

	public GoodsPageService() {
	}

	private Double getLeastPrice(Integer categoryId, String searchName) {
		PageRequest pageRequest = new PageRequest(0, 1, Direction.ASC, "price");
		Page<Product> productsPage = productRepository.findAll(new MinMaxPriceSpecification(categoryId, searchName),
				pageRequest);
		if (null != productsPage.getContent() && productsPage.getContent().size() > 0) {
			Product product = productsPage.getContent().get(0);
			return product.getPrice();
		}
		return defaultMinPrice;
	}

	private Double getBiggestPrice(Integer categoryId, String searchName) {
		PageRequest pageRequest = new PageRequest(0, 1, Direction.DESC, "price");
		Page<Product> productsPage = productRepository.findAll(new MinMaxPriceSpecification(categoryId, searchName),
				pageRequest);
		if (null != productsPage.getContent() && productsPage.getContent().size() > 0) {
			Product product = productsPage.getContent().get(0);
			return product.getPrice();
		}
		return defaultMaxPrice;
	}

	private Double getBiggestPrice() {
		PageRequest pageRequest = new PageRequest(0, 1, Direction.DESC, "price");
		Page<Product> productsPage = productRepository.findAll(new MinMaxPriceSpecification(null, null), pageRequest);
		Double biggestPrice = productsPage.getContent().get(0).getPrice();
		return biggestPrice;
	}

	private Integer getAmountOfPages(int amountOfItems) {
		Integer amount = (int) Math.ceil((double) amountOfItems / (double) PaginationHelper.getAmountOfCardsOnPage());
		return amount;
	}

	private Integer getAmountOfPages() {
		Integer amount = (int) Math.ceil(
				(double) productRepository.countByAvailable(1) / (double) PaginationHelper.getAmountOfCardsOnPage());
		return amount;
	}

	private Double getLeastPrice() {
		PageRequest pageRequest = new PageRequest(0, 1, Direction.ASC, "price");
		Page<Product> productsPage = productRepository.findAll(new MinMaxPriceSpecification(null, null), pageRequest);
		Double leastPrice = productsPage.getContent().get(0).getPrice();
		return leastPrice;
	}

	public GoodsPageInfo getGoodsPageInfo(Criteria criteria) {
		double minPrice = getLeastPrice(criteria.getCategoryId(), criteria.getSearchName());
		double maxPrice = getBiggestPrice(criteria.getCategoryId(), criteria.getSearchName());
		Double currentMinPrice = null;
		Double currentMaxPrice = null;
		int currentPage = criteria.getPage();

		if (!criteria.getAction().equalsIgnoreCase("category")) {
			currentMinPrice = criteria.getMinPrice();
			currentMaxPrice = criteria.getMaxPrice();
		}
		Integer category = null;
		if (criteria.getCategoryId() != 0) {
			category = criteria.getCategoryId();
		}
		int amountOfItems = productRepository.countBy(currentMinPrice, currentMaxPrice, category,
				criteria.getSearchName());
		int amountOfPages = getAmountOfPages(amountOfItems);

		if (currentPage == 0 || currentPage >= amountOfPages) {
			currentPage = 1;
		}
		List<Category> categories = categoryRepository.findByAvailable(1);
		GoodsPageInfo goodsPageInfo = new GoodsPageInfo.GoodsPageInfoBuilder(minPrice, maxPrice, sliderStep,
				amountOfPages, currentPage, categories, OrderByHelper.LIST_ORDER_BY).setCurrentMinPrice(currentMinPrice)
						.setCurrentMaxPrice(currentMaxPrice).build();
		return goodsPageInfo;
	}

	public GoodsPageInfo getGoodsPageInfo() {
		double minPrice = getLeastPrice();
		double maxPrice = getBiggestPrice();
		int amountOfPages = getAmountOfPages();
		List<Category> categories = categoryRepository.findByAvailable(1);
		GoodsPageInfo goodsPageInfo = new GoodsPageInfo.GoodsPageInfoBuilder(minPrice, maxPrice, sliderStep,
				amountOfPages, 1, categories, OrderByHelper.LIST_ORDER_BY).build();
		return goodsPageInfo;
	}

}
