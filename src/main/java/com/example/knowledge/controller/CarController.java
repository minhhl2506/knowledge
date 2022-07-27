package com.example.knowledge.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.knowledge.model.Car;
import com.example.knowledge.model.dto.CarDTO;
import com.example.knowledge.service.CarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

	private final CarService carService;

	// @GetMapping("/")
	// public String value() {
	// return this.carService.getValue();
	// }

	@GetMapping("/findAll")
	public List<Car> findAll() {
		return this.carService.findAll();
	}

	@GetMapping("/findAll1")
	public List<CarDTO> findAll1() {
		return this.carService.findAll1();
	}

	@GetMapping("/checkRegexPhoneNumber")
	public String checkRegexPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) {
		return this.carService.checkRegexPhoneNumber(phoneNumber);
	}

}
