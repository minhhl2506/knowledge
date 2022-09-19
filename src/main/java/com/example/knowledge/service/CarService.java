package com.example.knowledge.service;

import java.util.List;

import com.example.knowledge.model.Car;
import com.example.knowledge.model.dto.CarDTO;

public interface CarService {

//	public String getValue();

//	List<CarDTO> findAll();

	List<Car> findAll();

	List<CarDTO> findAll1();

//	String checkRegexPhoneNumber(String phoneNumber);

	CarDTO detail(Long id);

	List<CarDTO> search(String keyword);

	CarDTO create(CarDTO carDto);
	
	String getMessage();

}
