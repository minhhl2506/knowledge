package com.example.knowledge.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.knowledge.advice.BadRequestAlertException;
import com.example.knowledge.configuration.RsaProvider;
import com.example.knowledge.label.LabelKey;
import com.example.knowledge.label.Labels;
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

//	private final ValidationProperties validationProperties;

	@Override
	public Page<CarDTO> search(String keyword) {
		Pageable pageable = PageRequest.of(0, 100);

		List<Car> results = this.carRepository.searchByKeyWord(keyword, pageable);

		return new PageImpl<>(this.carMapper.toDto(results), pageable, this.carRepository.count(keyword));
	}

	@Override
	public String getMessage() {
		return Labels.getLabels(LabelKey.ERROR_CAR_NAME_IS_REQUIRED);
	}

	@Override
	public CarDTO create(CarDTO carDto) {
		Car car = this.carMapper.toEntity(carDto);

		car = this.carRepository.save_(car);

		return this.carMapper.toDto(car);
	}

	@Override
	public CarDTO detail(Long id) {

		if (Validator.isNull(id)) {
			throw new BadRequestAlertException(MessageCode.MSG1001);
		}

		Car car = this.carRepository.findCarById(id);

		if (Validator.isNull(car)) {
			throw new BadRequestAlertException(MessageCode.MSG1002);
		}

		return this.carMapper.toDto(car);
	}

}
