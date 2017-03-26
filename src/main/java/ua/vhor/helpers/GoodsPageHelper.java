package ua.vhor.helpers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.vhor.db.entity.Category;
import ua.vhor.entity.Criteria;
import ua.vhor.entity.GoodsPageInfo;
import ua.vhor.repository.ProductRepository;

public class GoodsPageHelper {

	@Autowired
	ProductRepository productRepository;

	private Criteria criteria;
	private List<Category> categories;

	public GoodsPageHelper(Criteria criteria) {
		super();
		this.criteria = criteria;
	}

	public GoodsPageInfo getGoodPageInfo() {
		return new GoodsPageInfo(criteria.getMinPrice(),
				criteria.getMaxPrice(), 1, 0, categories);
	}

	public GoodsPageHelper(Double minPrice, Double maxPrice,
			List<Category> categories) {
		super();
		if (minPrice == null || maxPrice == null) {
			minPrice = 0.0;
			maxPrice = 100.0;
		}
		this.criteria = new Criteria(minPrice, maxPrice);
		this.categories = categories;
	}
}
