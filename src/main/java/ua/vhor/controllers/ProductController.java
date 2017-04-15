package ua.vhor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.vhor.db.entity.Category;
import ua.vhor.db.entity.Product;
import ua.vhor.entity.Criteria;
import ua.vhor.entity.GoodsPageInfo;
import ua.vhor.helpers.GoodsPageHelper;
import ua.vhor.repository.CategoryRepository;
import ua.vhor.repository.ProductRepository;
import ua.vhor.utils.ParametersProvider;

@RestController
public class ProductController {

	private final int amountOfCardOnPage = Integer.parseInt(ParametersProvider
			.getProperty("pagination.amountcardsonpage"));

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@RequestMapping("/getProductsToCriteria")
	public List<Product> getProductAccordingCriteria(
			@RequestBody Criteria criteria) {
		List<Product> products = null;
		if (criteria.getAction().equalsIgnoreCase("category")) {
			if (criteria.getCategoryId() != 0) {
				products = productRepository.findByPriceBetweenAndCategoryId(
						null, null, criteria.getCategoryId(),
						criteria.getPage(), amountOfCardOnPage);
			} else {
				products = productRepository.findByPriceBetweenAndCategoryId(
						null, null, null, criteria.getPage(),
						amountOfCardOnPage);
			}
		} else if (criteria.getCategoryId() != 0) {
			products = productRepository.findByPriceBetweenAndCategoryId(
					criteria.getMinPrice(), criteria.getMaxPrice(),
					criteria.getCategoryId(), criteria.getPage(),
					amountOfCardOnPage);
		} else {
			products = productRepository.findByPriceBetweenAndCategoryId(
					criteria.getMinPrice(), criteria.getMaxPrice(), null,
					criteria.getPage(), amountOfCardOnPage);
		}
		return products;
	}

	@RequestMapping("/getGoodsInfo")
	public GoodsPageInfo getGoodsInfo(@RequestBody Criteria criteria) {
		Double minPrice = null;
		Double maxPrice = null;
		if (criteria.getCategoryId() != 0) {
			minPrice = productRepository.getLeastPriceByNameAndCategory(
					criteria.getCategoryId(), criteria.getSearchName());
			maxPrice = productRepository.getGreatestPriceByNameAndCategory(
					criteria.getCategoryId(), criteria.getSearchName());
		} else {
			minPrice = productRepository.getLeastPriceByNameAndCategory(null,
					criteria.getSearchName());
			maxPrice = productRepository.getGreatestPriceByNameAndCategory(
					null, criteria.getSearchName());
		}
		List<Category> categories = categoryRepository.findByAvailable(1);
		GoodsPageInfo goodsPageInfo = new GoodsPageHelper(minPrice, maxPrice,
				categories).getGoodPageInfo();
		return goodsPageInfo;
	}

	@RequestMapping(value = "/getGoodsInfoForFirstRequest")
	public GoodsPageInfo getGoodsInfoForFirstRequest() {
		double minPrice = productRepository.getLeastPriceByNameAndCategory(
				null, null);
		double maxPrice = productRepository.getGreatestPriceByNameAndCategory(
				null, null);
		List<Category> categories = categoryRepository.findByAvailable(1);

		GoodsPageInfo goodsPageInfo = new GoodsPageHelper(minPrice, maxPrice,
				categories).getGoodPageInfo();
		return goodsPageInfo;
	}
}
