package com.example.knowledge.repository;

import com.example.knowledge.model.dto.CarResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.knowledge.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

	@Query("SELECT new com.example.knowledge.model.dto.CarResponse(u.username , c.name, c.price) FROM User u " +
			"LEFT JOIN Car c on u.id = c.userId")
	public List<CarResponse> getJoinInfor();
	
}
