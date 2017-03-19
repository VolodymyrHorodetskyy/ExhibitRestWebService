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

@RestController
public class ProductController {

	private final int amountOfCardOnPage = 10;

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@RequestMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		Pageable pageable = new PageRequest(0, amountOfCardOnPage);
		List<Product> products = productRepository.findAll(pageable)
				.getContent();
		return products;
	}

	@RequestMapping("/getProductsToCriteria")
	public List<Product> getProductAccordingCriteria(
			@RequestBody Criteria criteria) {
		Pageable pageable = new PageRequest(criteria.getPage(),
				amountOfCardOnPage);
		List<Product> products = null;
		if (criteria.getCategoryId() != 0) {
			products = productRepository.findByPriceBetweenAndCategoryId(
					criteria.getMinPrice(), criteria.getMaxPrice(),
					criteria.getCategoryId(), pageable).getContent();
		} else {
			products = productRepository.findByPriceBetween(
					criteria.getMinPrice(), criteria.getMaxPrice(), pageable)
					.getContent();
		}

		return products;
	}

	@RequestMapping("/getGoodsInfo")
	public GoodsPageInfo getGoodsInfo(@RequestBody Criteria criteria) {
		double minPrice = 0;
		double maxPrice = 0;
		if (criteria.getCategoryId() != 0) {
			minPrice = productRepository.getLeastPriceByNameAndCategory(
					criteria.getCategoryId(), criteria.getSearchName());
			maxPrice = productRepository.getGreatestPriceByNameAndCategory(
					criteria.getCategoryId(), criteria.getSearchName());
		} else {
			minPrice = productRepository.getLeastPriceByName(criteria
					.getSearchName());
			maxPrice = productRepository.getGreatestPriceByName(criteria
					.getSearchName());
		}
		List<Category> categories = categoryRepository.findByAvailable(1);
		GoodsPageInfo goodsPageInfo = new GoodsPageHelper(minPrice, maxPrice,
				categories).getGoodPageInfo();
		return goodsPageInfo;
	}

	@RequestMapping(value = "/getGoodsInfoForFirstRequest")
	public GoodsPageInfo getGoodsInfoForFirstRequest() {
		double minPrice = productRepository.getLeastPrice();
		double maxPrice = productRepository.getGreatestPrice();
		List<Category> categories = categoryRepository.findByAvailable(1);

		GoodsPageInfo goodsPageInfo = new GoodsPageHelper(minPrice, maxPrice,
				categories).getGoodPageInfo();
		return goodsPageInfo;
	}
}
