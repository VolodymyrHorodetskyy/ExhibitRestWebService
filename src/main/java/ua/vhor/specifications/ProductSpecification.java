package ua.vhor.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import ua.vhor.db.entity.Category;
import ua.vhor.db.entity.Product;
import ua.vhor.db.entity.Product_;
import ua.vhor.entity.Criteria;

public class ProductSpecification implements Specification<Product> {

	private Criteria criteria;

	public ProductSpecification(Criteria criteria) {
		super();
		this.criteria = criteria;
	}

	private Predicate[] formPredicatesArray(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> listOfPredicates = new ArrayList<Predicate>();
		String searchName = criteria.getSearchName();
		Predicate availabilityPredicate = cb.equal(root.get(Product_.available), 1);
		listOfPredicates.add(availabilityPredicate);
		if (StringUtils.isNotBlank(searchName) && StringUtils.isNotEmpty(searchName)) {
			Predicate namePredicate = cb.like(root.get(Product_.name),
					String.format("%s" + searchName + "%s", "%", "%"));
			listOfPredicates.add(namePredicate);
		}

		if (criteria.getCategoryId() != 0) {
			Category category = new Category();
			category.setId(criteria.getCategoryId());
			Predicate categoryPredicate = cb.equal(root.get(Product_.category), category);
			listOfPredicates.add(categoryPredicate);
		}

		if (!criteria.getAction().equalsIgnoreCase("category")) {
			Predicate minPricePredicate = cb.greaterThanOrEqualTo(root.get(Product_.price), criteria.getMinPrice());
			Predicate maxPricePredicate = cb.lessThanOrEqualTo(root.get(Product_.price), criteria.getMaxPrice());
			listOfPredicates.add(minPricePredicate);
			listOfPredicates.add(maxPricePredicate);
		}
		return listOfPredicates.toArray(new Predicate[listOfPredicates.size()]);

	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate[] predicates = formPredicatesArray(root, query, cb);
		return cb.and(predicates);
	}

}
