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

public class MinMaxPriceSpecification implements Specification<Product> {

	private Integer categoryId;
	private String searchName;

	public MinMaxPriceSpecification(Integer categoryId, String searchName) {
		super();
		this.categoryId = categoryId;
		this.searchName = searchName;
	}

	private Predicate[] formPredicatesArray(Root<Product> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> listOfPredicates = new ArrayList<Predicate>();
		Predicate availabilityPredicate = cb.equal(
				root.get(Product_.available), "1");
		listOfPredicates.add(availabilityPredicate);
		if (null != categoryId && categoryId != 0) {
			Category category = new Category();
			category.setId(categoryId);
			Predicate categoryPredicate = cb.equal(root.get(Product_.category),
					category);
			listOfPredicates.add(categoryPredicate);
		}
		if (StringUtils.isNotBlank(searchName)) {
			Predicate searchNamePredicate = cb.like(root.get(Product_.name),
					String.format("%s" + searchName + "%s", "%", "%"));
			listOfPredicates.add(searchNamePredicate);
		}

		return listOfPredicates.toArray(new Predicate[listOfPredicates.size()]);
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Predicate[] predicates = formPredicatesArray(root, query, cb);
		return cb.and(predicates);
	}

}
