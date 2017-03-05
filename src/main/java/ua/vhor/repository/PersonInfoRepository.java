package ua.vhor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.vhor.db.entity.PersonInfo;

public interface PersonInfoRepository extends JpaRepository<PersonInfo, Long> {

}
