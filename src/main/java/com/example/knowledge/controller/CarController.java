package com.example.knowledge.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.knowledge.annotation.InboundRequestLog;
import com.example.knowledge.model.AClass;
import com.example.knowledge.model.dto.CarDTO;
import com.example.knowledge.model.dto.CarResponse;
import com.example.knowledge.repository.UserRepository;
import com.example.knowledge.service.CarService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

	private final CarService carService;

	private final UserRepository userRepository; 

	@InboundRequestLog
	@PostMapping("/create")
	public ResponseEntity<CarDTO> create(HttpServletRequest request, @Valid @RequestBody CarDTO carDto) {
		return ResponseEntity.ok().body(this.carService.create(carDto));
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<CarDTO> detail(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.carService.detail(id));
	}
	
	@GetMapping("/locale")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String locale() {
		return this.carService.getMessage(); 
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<CarDTO>> search(@RequestParam(value = "keyword", required = false) String keyword) {
		return new ResponseEntity<Page<CarDTO>>(this.carService.search(keyword), HttpStatus.OK);
	}
	
	@GetMapping("/search-by-keyword")
	public ResponseEntity<Page<CarDTO>> searchByKeyword(@RequestParam(value = "keyword", required = false) String keyword) {
		return new ResponseEntity<Page<CarDTO>>(this.carService.searchByKeyword(keyword), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		this.carService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<CarDTO> update(@Valid @RequestBody CarDTO dto) {
		return ResponseEntity.ok().body(this.carService.update(dto));
	}

	@GetMapping("/join")
	public ResponseEntity<List<CarResponse>> join() {
		return new ResponseEntity<List<CarResponse>>(this.userRepository.getJoinInfor(), HttpStatus.OK);
	}
	
	@GetMapping("/3rd-party")
    public ResponseEntity<?> getCountry() {
        try {
        	RestTemplate restTemplate = new RestTemplate();
        	String thirdPartyApiUrl
        	  = "http://localhost:9090/hello";
        	ResponseEntity<String> response
        	  = restTemplate.exchange(thirdPartyApiUrl, HttpMethod.GET, null, String.class);
        	Gson gson = new Gson();
        	JsonObject convertedObject = gson.fromJson(response.getBody(), JsonObject.class);
        	AClass a = new AClass();
        	a.setAId(convertedObject.get("id").getAsInt());
        	a.setAName(convertedObject.get("name").getAsString());
            return new ResponseEntity<>(a, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
