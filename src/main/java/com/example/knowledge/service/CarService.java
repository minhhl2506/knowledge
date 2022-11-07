package com.example.knowledge.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.knowledge.model.Car;
import com.example.knowledge.model.dto.CarDTO;

public interface CarService {

//	public String getValue();

//	List<CarDTO> findAll();

	List<Car> findAll();

	Page<Car> findAll1(int pageIndex, int pageSize);

//	String checkRegexPhoneNumber(String phoneNumber);

	CarDTO detail(Long id);

	Page<CarDTO> search(String keyword);

	CarDTO create(CarDTO carDto);
	
	String getMessage();

}
