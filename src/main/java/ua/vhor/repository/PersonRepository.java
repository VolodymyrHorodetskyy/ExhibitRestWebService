package ua.vhor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.vhor.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findByLogin(String login);
	
	List<Person> findByLoginAndPassword(String login, String password);
	
	
}
