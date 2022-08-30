package com.example.knowledge.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.knowledge.message.MessageCode;
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

//	@GetMapping("/findAll")
//	public List<Car> findAll() {
//		return this.carService.findAll();
//	}
	
	@Autowired   
	private MessageSource messageSource;  
	
	@PostMapping("/create")
	public ResponseEntity<CarDTO> create(@Valid @RequestBody CarDTO carDto) {
		return ResponseEntity.ok().body(this.carService.create(carDto));
	}

	
	@GetMapping("/findAll1")
	public ResponseEntity<List<CarDTO>> findAll1() {
		return ResponseEntity.ok(this.carService.findAll1());
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<CarDTO> detail(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.carService.detail(id));
	}

	@GetMapping("/checkRegexPhoneNumber")
	public String checkRegexPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) {
		return this.carService.checkRegexPhoneNumber(phoneNumber);
	}
	
	@GetMapping("/locale")
	public String locale() {
//		String[] languages = Locale.getISOLanguages();
//		String[] countries = Locale.getISOCountries();
//		for(String language: languages) {
//			System.out.println(language);
//		}
//		for(String country: countries) {
//			System.out.println(country);
//		}
		return this.carService.getMessage(); 
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<CarDTO>> search(@RequestParam(value = "keyword", required = false) String keyword) {
		return new ResponseEntity<List<CarDTO>>(this.carService.search(keyword), HttpStatus.OK);
	}

}
