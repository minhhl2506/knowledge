package com.example.knowledge.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knowledge.model.Car;

@Repository	
public interface CarRepository extends JpaRepository<Car, Long> {
	
	@Cacheable(cacheNames = "findById", key = "#id", unless = "#result == null")
	default Car findCarById(Long id) {
		return this.findById(id).orElse(null);
	}
}	
