package com.example.knowledge.repository.extend;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.knowledge.model.Car;


public interface CarRepositoryExtend {
	List<Car> searchByKeyWord(String keyword, Pageable pageable);
	
	Long count(String keyword);
}	
