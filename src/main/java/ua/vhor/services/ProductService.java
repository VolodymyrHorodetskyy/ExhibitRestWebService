package ua.vhor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ua.vhor.db.entity.Product;
import ua.vhor.entity.Criteria;
import ua.vhor.helpers.PaginationHelper;
import ua.vhor.repository.ProductRepository;
import ua.vhor.specifications.MinMaxPriceSpecification;
import ua.vhor.specifications.ProductSpecification;

@ComponentScan(basePackages = { "ua.vhor.repository" })
@Service
public class ProductService {

	private final int amountOfCards = PaginationHelper.getAmountOfPages();

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getProductsAccordingToCriteria(Criteria criteria) {
		PageRequest pageRequest = getPageRequestAccordingToCriteria(criteria);

		Page<Product> productsPage = productRepository.findAll(
				new ProductSpecification(criteria), pageRequest);
		List<Product> productsList = productsPage.getContent();
		return productsList;
	}

	public Double getLeastPrice(Integer categoryId, String searchName) {
		PageRequest pageRequest = new PageRequest(0, 1, Direction.ASC, "price");
		Page<Product> productsPage = productRepository.findAll(
				new MinMaxPriceSpecification(categoryId, searchName),
				pageRequest);
		Double leastPrice = productsPage.getContent().get(0).getPrice();

		return leastPrice;
	}

	public Double getBiggestPrice(Integer categoryId, String searchName) {
		PageRequest pageRequest = new PageRequest(0, 1, Direction.DESC, "price");
		Page<Product> productsPage = productRepository.findAll(
				new MinMaxPriceSpecification(categoryId, searchName),
				pageRequest);
		Double biggestPrice = productsPage.getContent().get(0).getPrice();

		return biggestPrice;
	}

	private PageRequest getPageRequestAccordingToCriteria(Criteria criteria) {
		Sort sort = null;
		if (criteria.getOrderBy().equalsIgnoreCase("Newest")
				|| null == criteria.getOrderBy()) {
			sort = new Sort(Sort.Direction.DESC, "createdDate");
		} else if (criteria.getOrderBy().equalsIgnoreCase("Highest Price")) {
			sort = new Sort(Sort.Direction.DESC, "price");
		} else if (criteria.getOrderBy().equalsIgnoreCase("Lowest Price")) {
			sort = new Sort(Sort.Direction.ASC, "price");
		} else if (criteria.getOrderBy().equalsIgnoreCase("Name")) {
			sort = new Sort(Sort.Direction.ASC, "name");
		}
		PageRequest pageRequest = new PageRequest(criteria.getPage(),
				amountOfCards, sort);

		return pageRequest;
	}

}
