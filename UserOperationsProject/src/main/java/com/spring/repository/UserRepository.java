package com.spring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.userId = ?1 OR u.emailId = ?2 OR u.phoneNumber= ?3")
	User findByUserIdOrEmailIdOrPhoneNumber(String userId, String emailId, String phoneNumber);

	@Query("select x from User x where x.createDate between ?1 and ?2")
	List<User> findByCreateDateBetween(LocalDate date1, LocalDate date2);

}
