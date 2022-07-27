package com.example.knowledge.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.knowledge.configuration.RsaProvider;
import com.example.knowledge.configuration.ValidationProperties;
import com.example.knowledge.model.Car;
import com.example.knowledge.model.dto.CarDTO;
import com.example.knowledge.repository.CarRepository;
import com.example.knowledge.service.CarService;
import com.example.knowledge.service.mapper.CarMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

	private final CarRepository carRepository;

//	private final PropertiesConfiguration propertiesConfiguration;

	private final CarMapper carMapper;

	private final RsaProvider rsaProvider;
	
	private final ValidationProperties validationProperties;

//	@Override
//	public String getValue() {
//
//		System.out.println(this.propertiesConfiguration.getAlgorithm() + " "
//				+ this.propertiesConfiguration.getIvGeneratorClassname() + " "
//				+ this.propertiesConfiguration.getKeyObtentionIterations());
//		return this.propertiesConfiguration.getPass();
//	}

	@Override
	public List<CarDTO> findAll1() {

		String encrypt;
		String decrypt;
		
		try {
			
			encrypt = rsaProvider.encrypt("minhmomi");
			decrypt = this.rsaProvider.decrypt(encrypt);
			
			System.out.println(encrypt);
			System.out.println(decrypt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Car> cars = this.carRepository.findAll();

		return this.carMapper.toDto(cars);
	}

	@Override
	public List<Car> findAll() {
		List<Car> cars = this.carRepository.findAll();

		List<CarDTO> carDtos = this.carMapper.toDto(cars);

		return this.carMapper.toEntity(carDtos);
	}
	
	@Override
	public String checkRegexPhoneNumber(String phoneNumber) {
		if(!this.validationProperties.isPhoneNumberValid(phoneNumber)) {
			return "SĐT không hợp lệ";
		}
		
		return phoneNumber;
	}

}
