package com.example.knowledge.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.example.knowledge.model.Car;
import com.example.knowledge.model.dto.CarDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper extends EntityMapper<CarDTO, Car> {
	
//	@Mapping(target = "carId", ignore = true) // Không map id từ Car sang CarDTO
	@Mapping(source = "name", target = "carName") // Map thuộc tính "name" trong Car sang "carName" trong CarDTO
	@Mapping(source = "price", target = "carPrice") // Map thuộc tính "price" trong Car sang "carPrice" trong CarDTO
	public CarDTO toDto(Car car);
	
	@Mapping(target = "id", ignore = true) // Không map id từ Car sang CarDTO
	@Mapping(source = "carName", target = "name") // Map thuộc tính "name" trong Car sang "carName" trong CarDTO
	@Mapping(source = "carPrice", target = "price") // Map thuộc tính "price" trong Car sang "carPrice" trong CarDTO
	public Car toEntity(CarDTO carDto);

}
