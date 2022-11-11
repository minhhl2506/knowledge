package com.example.knowledge.repository.extend;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.knowledge.model.Car;
import com.example.knowledge.model.ResultSet;


public interface CarRepositoryExtend {
	List<Car> search(String keyword, Pageable pageable);
	
	Long count(String keyword);
	
	ResultSet<Car> searchByKeyword(String keyword, Pageable pageable);
}	
