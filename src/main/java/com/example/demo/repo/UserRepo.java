package com.example.demo.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    
//	@Query()
//	List<Map<String, Object>> getAllUser();
	
	@Query(value = "select * from user", nativeQuery = true)
	List<Map<String, Object>> findAllUser();

//	User search(String search);
	
//	@Query(value = "select * from user", nativeQuery = true)
//	List<?> findAllUser();
	
	@Query(value = "SELECT * FROM user WHERE name LIKE %?1%", nativeQuery = true)
	List<User> search(String search);
}
