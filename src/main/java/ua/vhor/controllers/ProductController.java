package ua.vhor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import ua.vhor.services.ProductService;
import ua.vhor.utils.ParametersProvider;

@RestController
public class ProductController {

	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductService productService;

	@RequestMapping("/getProductsToCriteria")
	public List<Product> getProductAccordingCriteria(
			@RequestBody Criteria criteria) {
		List<Product> products = null;
		products = productService.getProductsAccordingToCriteria(criteria);
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
