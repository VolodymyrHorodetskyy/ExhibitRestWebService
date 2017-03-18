package ua.vhor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.vhor.db.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByAvailable(int available);

}
