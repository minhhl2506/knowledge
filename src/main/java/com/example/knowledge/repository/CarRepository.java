package com.example.knowledge.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knowledge.cache.util.CacheConstants;
import com.example.knowledge.model.Car;
import com.example.knowledge.repository.extend.CarRepositoryExtend;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, CarRepositoryExtend {

	@Cacheable(cacheNames = CacheConstants.Car.FIND_CAR_BY_ID, key = "#id", unless = "#result == null")
	default Car findCarById(Long id) {
		return this.findById(id).orElse(null);
	}
	
	default Car save_(Car car) {
		return this.save(car);
	}
	
}
