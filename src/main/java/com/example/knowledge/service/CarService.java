package com.example.knowledge.service;

import org.springframework.data.domain.Page;

import com.example.knowledge.model.dto.CarDTO;

public interface CarService {

	CarDTO detail(Long id);

	Page<CarDTO> search(String keyword);
	
	Page<CarDTO> searchByKeyword(String keyword);

	CarDTO create(CarDTO carDto);
	
	String getMessage();

	void delete(Long id);

	CarDTO update(CarDTO dto);

	String encrypt() throws Exception;

	String decrypt(String str) throws Exception;

}
