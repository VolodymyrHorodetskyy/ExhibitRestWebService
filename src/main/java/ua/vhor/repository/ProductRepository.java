package ua.vhor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import ua.vhor.db.entity.Category;
import ua.vhor.db.entity.Product;

public interface ProductRepository extends
		PagingAndSortingRepository<Product, Long> {

	static final String GET_BIGGEST_PRICE_QUERY = "SELECT MAX(p.es_pdct_price) FROM es_product p where es_pdct_available = 1";
	static final String GET_LEAST_PRICE_QUERY = "SELECT MIN(p.es_pdct_price) FROM es_product p where es_pdct_available = 1";
	static final String GET_BIGGEST_PRICE_QUERY_BY_NAME_AND_CATEGORY = "SELECT MAX(p.es_pdct_price) FROM es_product p where es_pdct_available = 1 and es_pdct_category =?1 and es_pdct_name like %?2%";
	static final String GET_LEAST_PRICE_QUERY_BY_NAME_AND_CATEGORY = "SELECT MIN(p.es_pdct_price) FROM es_product p where es_pdct_available = 1 and es_pdct_category =?1 and es_pdct_name like %?2%";
	static final String GET_BIGGEST_PRICE_QUERY_BY_NAME = "SELECT MAX(p.es_pdct_price) FROM es_product p where es_pdct_available = 1 and es_pdct_name like %?1%";
	static final String GET_LEAST_PRICE_QUERY_BY_NAME = "SELECT MIN(p.es_pdct_price) FROM es_product p where es_pdct_available = 1 and es_pdct_name like %?1%";

	public Page<Product> findAll(Pageable pageable);

	public Page<Product> findByPriceBetweenAndCategoryId(double min,
			double max, int categorId, Pageable pageable);

	@Query(value = GET_BIGGEST_PRICE_QUERY, nativeQuery = true)
	public Double getGreatestPrice();

	@Query(value = GET_LEAST_PRICE_QUERY, nativeQuery = true)
	public Double getLeastPrice();

	Page<Product> findByPriceBetween(double min, double max, Pageable pageable);

	@Query(value = GET_LEAST_PRICE_QUERY_BY_NAME_AND_CATEGORY, nativeQuery = true)
	public Double getLeastPriceByNameAndCategory(int productId, String name);

	@Query(value = GET_BIGGEST_PRICE_QUERY_BY_NAME_AND_CATEGORY, nativeQuery = true)
	public Double getGreatestPriceByNameAndCategory(int productId, String name);

	@Query(value = GET_LEAST_PRICE_QUERY_BY_NAME, nativeQuery = true)
	public Double getLeastPriceByName(String name);

	@Query(value = GET_BIGGEST_PRICE_QUERY_BY_NAME, nativeQuery = true)
	public Double getGreatestPriceByName(String name);

}
