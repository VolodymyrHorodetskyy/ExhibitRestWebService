package ua.vhor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.vhor.db.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

	static final String GET_BIGGEST_PRICE_QUERY_BY_NAME_AND_CATEGORY = "SELECT MAX(ep.es_pdct_price) FROM es_product ep WHERE ep.es_pdct_available=1 AND ep.es_pdct_category = COALESCE(NULLIF(:category, ''), ep.es_pdct_category) AND ep.es_pdct_name LIKE CONCAT('%', COALESCE(NULLIF(:name, ''), ep.es_pdct_name), '%');";
	static final String GET_LEAST_PRICE_QUERY_BY_NAME_AND_CATEGORY = "SELECT MIN(ep.es_pdct_price) FROM es_product ep WHERE ep.es_pdct_available=1 AND ep.es_pdct_category = COALESCE(NULLIF(:category, ''), ep.es_pdct_category) AND ep.es_pdct_name LIKE CONCAT('%', COALESCE(NULLIF(:name, ''), ep.es_pdct_name), '%');";

	public Page<Product> findAll(Pageable pageable);

	@Query(value = GET_PRODUCTS_BY_PRICE_AND_CATEGORY, nativeQuery = true)
	public List<Product> findByPriceBetweenAndCategoryId(
			@Param("minPrice") Double min, @Param("maxPrice") Double max,
			@Param("categoryId") Integer categorId, @Param("name") String name,
			@Param("page") Integer page,
			@Param("quantityofcards") Integer quantityOfCards);

	Page<Product> findByPriceBetween(double min, double max, Pageable pageable);

	@Query(value = GET_LEAST_PRICE_QUERY_BY_NAME_AND_CATEGORY, nativeQuery = true)
	public Double getLeastPriceByNameAndCategory(
			@Param("category") Integer categoryId, @Param("name") String name);

	@Query(value = GET_BIGGEST_PRICE_QUERY_BY_NAME_AND_CATEGORY, nativeQuery = true)
	public Double getGreatestPriceByNameAndCategory(
			@Param("category") Integer categoryId, @Param("name") String name);

}
