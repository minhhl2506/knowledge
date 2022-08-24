package com.example.knowledge.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.knowledge.advice.BadRequestAlertException;
import com.example.knowledge.configuration.RsaProvider;
import com.example.knowledge.configuration.ValidationProperties;
import com.example.knowledge.message.MessageCode;
import com.example.knowledge.model.Car;
import com.example.knowledge.model.dto.CarDTO;
import com.example.knowledge.repository.CarRepository;
import com.example.knowledge.service.CarService;
import com.example.knowledge.service.mapper.CarMapper;
import com.example.knowledge.util.Validator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {
	
	@PersistenceContext
    private EntityManager entityManager;

	private final CarRepository carRepository;

//	private final PropertiesConfiguration propertiesConfiguration;

	private final CarMapper carMapper;

	private final RsaProvider rsaProvider;
	
	private final ValidationProperties validationProperties;
	
	@Override
	public List<CarDTO> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDTO> findAll1() {

//		String encrypt;
//		String decrypt;
//		
//		try {
//			
//			encrypt = rsaProvider.encrypt("minhmomi");
//			decrypt = this.rsaProvider.decrypt(encrypt);
//			
//			System.out.println(encrypt);
//			System.out.println(decrypt);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		List<Car> cars = this.carRepository.findAll();

		return this.carMapper.toDto(cars);
	}
	
	@Override
	public CarDTO create(CarDTO carDto) {
		Car car = this.carMapper.toEntity(carDto);
		
		car = this.carRepository.save(car);
		
		return this.carMapper.toDto(car);
	}

	@Override
	public CarDTO detail(Long id) {
		
		if(Validator.isNull(id)) {
			throw new BadRequestAlertException(MessageCode.MSG1001);
		}
		
		Car car = this.carRepository.findCarById(id);
		
		if(Validator.isNull(car)) {
			throw new BadRequestAlertException(MessageCode.MSG1002);
		}

		return this.carMapper.toDto(car);
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
