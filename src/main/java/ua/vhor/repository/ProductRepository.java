package ua.vhor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.vhor.db.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>,
		JpaSpecificationExecutor {

	public Page<Product> findAll(Specification spec, Pageable pageable);

	public Integer countBy(Specification spec);

	public Integer countByAvailable(int available);
}
