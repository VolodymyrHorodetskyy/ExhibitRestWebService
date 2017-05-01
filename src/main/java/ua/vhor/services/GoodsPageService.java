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
		Page<Product> productsPage = productRepository.findAll(
				new MinMaxPriceSpecification(categoryId, searchName),
				pageRequest);
		if (null != productsPage.getContent()
				&& productsPage.getContent().size() > 0) {
			Product product = productsPage.getContent().get(0);
			return product.getPrice();
		}
		return defaultMinPrice;
	}

	private Double getBiggestPrice(Integer categoryId, String searchName) {
		PageRequest pageRequest = new PageRequest(0, 1, Direction.DESC, "price");
		Page<Product> productsPage = productRepository.findAll(
				new MinMaxPriceSpecification(categoryId, searchName),
				pageRequest);
		if (null != productsPage.getContent()
				&& productsPage.getContent().size() > 0) {
			Product product = productsPage.getContent().get(0);
			return product.getPrice();
		}
		return defaultMaxPrice;
	}

	private Double getBiggestPrice() {
		PageRequest pageRequest = new PageRequest(0, 1, Direction.DESC, "price");
		Page<Product> productsPage = productRepository.findAll(
				new MinMaxPriceSpecification(null, null), pageRequest);
		Double biggestPrice = productsPage.getContent().get(0).getPrice();
		return biggestPrice;
	}

	private Double getLeastPrice() {
		PageRequest pageRequest = new PageRequest(0, 1, Direction.ASC, "price");
		Page<Product> productsPage = productRepository.findAll(
				new MinMaxPriceSpecification(null, null), pageRequest);
		Double leastPrice = productsPage.getContent().get(0).getPrice();

		return leastPrice;
	}

	public GoodsPageInfo getGoodsPageInfo(Criteria criteria) {
		double minPrice = getLeastPrice(criteria.getCategoryId(),
				criteria.getSearchName());
		double maxPrice = getBiggestPrice(criteria.getCategoryId(),
				criteria.getSearchName());

		List<Category> categories = categoryRepository.findByAvailable(1);
		GoodsPageInfo goodsPageInfo = new GoodsPageInfo(minPrice, maxPrice,
				sliderStep, 1, categories, OrderByHelper.LIST_ORDER_BY);
		return goodsPageInfo;
	}

	public GoodsPageInfo getGoodsPageInfo() {
		double minPrice = getLeastPrice();
		double maxPrice = getBiggestPrice();
		List<Category> categories = categoryRepository.findByAvailable(1);
		GoodsPageInfo goodsPageInfo = new GoodsPageInfo(minPrice, maxPrice,
				sliderStep, 1, categories, OrderByHelper.LIST_ORDER_BY);
		return goodsPageInfo;
	}

}
