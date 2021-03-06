package ua.vhor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ua.vhor.db.entity.Product;
import ua.vhor.entity.Criteria;
import ua.vhor.helpers.PaginationHelper;
import ua.vhor.repository.ProductRepository;
import ua.vhor.specifications.ProductSpecification;

@Service
public class ProductService {

	private final int amountOfCards = PaginationHelper.getAmountOfCardsOnPage();

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getProductsAccordingToCriteria(Criteria criteria) {
		PageRequest pageRequest = getPageRequestAccordingToCriteria(criteria);

		Page<Product> productsPage = productRepository.findAll(new ProductSpecification(criteria), pageRequest);
		List<Product> productsList = productsPage.getContent();
		return productsList;
	}

	private PageRequest getPageRequestAccordingToCriteria(Criteria criteria) {
		Sort sort = null;
		if (criteria.getOrderBy().equalsIgnoreCase("Newest") || null == criteria.getOrderBy()) {
			sort = new Sort(Sort.Direction.DESC, "createdDate");
		} else if (criteria.getOrderBy().equalsIgnoreCase("Highest Price")) {
			sort = new Sort(Sort.Direction.DESC, "price");
		} else if (criteria.getOrderBy().equalsIgnoreCase("Lowest Price")) {
			sort = new Sort(Sort.Direction.ASC, "price");
		} else if (criteria.getOrderBy().equalsIgnoreCase("Name")) {
			sort = new Sort(Sort.Direction.ASC, "name");
		}
		int page = criteria.getPage();
		if(page == 0){
			page = 1;
		}
		PageRequest pageRequest = new PageRequest(page - 1, amountOfCards, sort);

		return pageRequest;
	}

}
