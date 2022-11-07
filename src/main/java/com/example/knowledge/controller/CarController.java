package com.example.knowledge.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.knowledge.annotation.InboundRequestLog;
import com.example.knowledge.model.Car;
import com.example.knowledge.model.dto.CarDTO;
import com.example.knowledge.model.dto.CarResponse;
import com.example.knowledge.repository.UserRepository;
import com.example.knowledge.service.CarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

	private final CarService carService;

	private final UserRepository userRepository;

	// @GetMapping("/")
	// public String value() {
	// return this.carService.getValue();
	// }

//	@GetMapping("/findAll")
//	public List<Car> findAll() {
//		return this.carService.findAll();
//	}  
	
	@InboundRequestLog
	@PostMapping("/create")
	public ResponseEntity<CarDTO> create(HttpServletRequest request, @Valid @RequestBody CarDTO carDto) {
		return ResponseEntity.ok().body(this.carService.create(carDto));
	}

	@InboundRequestLog
	@GetMapping("/findAll/{pageIndex}/{pageSize}")
	public ResponseEntity<Page<Car>> findAll1(HttpServletRequest request, @PathVariable int pageIndex, @PathVariable int pageSize) {
		return ResponseEntity.ok(this.carService.findAll1(pageIndex, pageSize));
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<CarDTO> detail(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.carService.detail(id));
	}

//	@GetMapping("/checkRegexPhoneNumber")
//	public String checkRegexPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) {
//		return this.carService.checkRegexPhoneNumber(phoneNumber);
//	}
	
	@GetMapping("/locale")
	public String locale() {
		return this.carService.getMessage(); 
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<CarDTO>> search(@RequestParam(value = "keyword", required = false) String keyword) {
		return new ResponseEntity<Page<CarDTO>>(this.carService.search(keyword), HttpStatus.OK);
	}

	@GetMapping("/join")
	public ResponseEntity<List<CarResponse>> join() {
		return new ResponseEntity<List<CarResponse>>(this.userRepository.getJoinInfor(), HttpStatus.OK);
	}

}
