package ua.vhor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.vhor.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAll();

}
