package ua.vhor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ua.vhor.db.entity.Category;
import ua.vhor.db.entity.Product;
import ua.vhor.entity.Criteria;
import ua.vhor.repository.ProductRepository;
import ua.vhor.specifications.ProductSpecification;

@ComponentScan(basePackages = { "ua.vhor.repository" })
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getProductsAccordingToCriteria(Criteria criteria, int amountOfCards) {
		Category category = null;
		if (criteria.getCategoryId() != 0) {
			category = new Category();
			category.setId(criteria.getCategoryId());
		}
		Page<Product> productsPage = productRepository.findAll(
				new ProductSpecification(criteria), new PageRequest(criteria.getPage(), amountOfCards));
		List<Product> productsList = productsPage.getContent();
		return productsList;
	}
}
