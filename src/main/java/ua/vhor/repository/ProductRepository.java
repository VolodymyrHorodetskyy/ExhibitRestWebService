package ua.vhor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.vhor.db.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor {

	final String COUNT_AMOUNT_OF_PRODUCTS_QUERY = "SELECT COUNT(*) FROM es_product ep where ep.es_pdct_available=1 AND ep.es_pdct_price BETWEEN  COALESCE(:minPrice, 0) AND COALESCE(:maxPrice, (select max(es_pdct_price) from exhibit.es_product ep1 where es_pdct_available =1)) and ep.es_pdct_category = COALESCE(NULLIF(:categoryId, ''), ep.es_pdct_category)  AND ep.es_pdct_name LIKE CONCAT('%', COALESCE(NULLIF(:name, ''), ep.es_pdct_name), '%') ";

	public Page<Product> findAll(Specification spec, Pageable pageable);

	@Query(value = COUNT_AMOUNT_OF_PRODUCTS_QUERY, nativeQuery = true)
	public Integer countBy(@Param("minPrice") Double min, @Param("maxPrice") Double max,
			@Param("categoryId") Integer categorId, @Param("name") String name);

	public Integer countByAvailable(int available);
}
