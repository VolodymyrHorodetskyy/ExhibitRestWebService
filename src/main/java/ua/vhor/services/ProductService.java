package ua.vhor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ua.vhor.db.entity.Product;
import ua.vhor.entity.Criteria;
import ua.vhor.helpers.PaginationHelper;
import ua.vhor.repository.ProductRepository;
import ua.vhor.specifications.ProductSpecification;

@ComponentScan(basePackages = { "ua.vhor.repository" })
@Service
public class ProductService {

	private final int amountOfCards = PaginationHelper.getAmountOfPages();

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getProductsAccordingToCriteria(Criteria criteria) {
		PageRequest pageRequest = new PageRequest(criteria.getPage(),
				amountOfCards, Sort.Direction.ASC, "name");

		Page<Product> productsPage = productRepository.findAll(
				new ProductSpecification(criteria), pageRequest);
		List<Product> productsList = productsPage.getContent();
		return productsList;
	}
}
