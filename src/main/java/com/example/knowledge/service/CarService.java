package com.example.knowledge.service;

import org.springframework.data.domain.Page;

import com.example.knowledge.model.dto.CarDTO;

public interface CarService {

	CarDTO detail(Long id);

	Page<CarDTO> search(String keyword);
	
	Page<CarDTO> searchByKeyword(String keyword);

	CarDTO create(CarDTO carDto);
	
	String getMessage();

}
