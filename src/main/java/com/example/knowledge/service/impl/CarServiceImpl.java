package com.example.knowledge.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
	public List<CarDTO> search(String keyword) {
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
//
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
//                .forEntity(Car.class)
//                .overridesForField(Car.FieldName.POSITION_NAME, Constants.AnalyzerDefName.EDGE_NGRAM_QUERY)
//                .overridesForField(Position.FieldName.POSITION_SHORT_NAME, Constants.AnalyzerDefName.EDGE_NGRAM_QUERY)
//                .overridesForField(Position.FieldName.POSITION_CODE, Constants.AnalyzerDefName.EDGE_NGRAM_QUERY)
//                .get();
//
//        BooleanJunction<?> mustJunc = queryBuilder.bool();
//
////        mustJunc = mustJunc.must(queryBuilder.keyword().onField(Position.FieldName.STATUS)
////                        .matching(EntityStatus.DELETED.getStatus()).createQuery()).not();
////
////        if (Validator.isNotNull(params.getStatus())) {
////            mustJunc = mustJunc
////                            .must(queryBuilder.keyword().onField(Position.FieldName.STATUS).matching(params.getStatus())
////                                            .createQuery());
////        }
//
//        if (!Validator.isNull(keyword)) {
//            mustJunc = mustJunc.must(queryBuilder.keyword().wildcard()
//                            .onField(Position.FieldName.POSITION_CODE)
//                            .matching(QueryUtil.getFullWildcardParam(params.getPositionCode()))
//                            .createQuery());
//        }
//
//        if (Validator.isNotNull(params.getPositionName())) {
//            mustJunc = mustJunc.must(queryBuilder.keyword().onField(Position.FieldName.POSITION_NAME)
//                            .matching(params.getPositionName().toLowerCase())
//                            .createQuery());
//        }
//
//        if (Validator.isNotNull(params.getShortName())) {
//            mustJunc = mustJunc.must(queryBuilder.keyword().onField(Position.FieldName.POSITION_SHORT_NAME)
//                            .matching(params.getShortName().toLowerCase())
//                            .createQuery());
//        }
//
//        if (Validator.isNull(keyword)) {
//            BooleanJunction<?> shouldJunc = queryBuilder.bool();
//
//            shouldJunc = shouldJunc
//                            .should(queryBuilder
//                                            .keyword()
//                                            .onFields(Position.FieldName.POSITION_NAME,
//                                                            Position.FieldName.POSITION_SHORT_NAME)
//                                            .matching(keyword.toLowerCase()).createQuery())
//                            .should(queryBuilder
//                                            .keyword()
//                                            .wildcard()
//                                            .onFields(Position.FieldName.POSITION_CODE)
//                                            .matching(QueryUtil.getFullWildcardParam(keyword)).createQuery());
//
//            mustJunc = mustJunc.must(shouldJunc.createQuery());
//        }
//
//        org.apache.lucene.search.Query query = mustJunc.createQuery();
//
//        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Position.class);
//
//        Sort sort = queryBuilder.sort()
//                .byScore().desc()
//                .andByField(Position.SortName.POSITION_ID_SORT).desc()
//                .createSort();
//
//        jpaQuery.setSort(sort);
//
//        int count = jpaQuery.getResultSize();
//
////        if (pageable != null) {
////            jpaQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
////            jpaQuery.setMaxResults(pageable.getPageSize());
////        } else {
////            jpaQuery.setFirstResult(QueryUtil.FIRST_INDEX);
////            jpaQuery.setMaxResults(QueryUtil.MAX_RESULT);
////        }
//
//        return new List<>(jpaQuery.getResultList(), count);
		
		return null;
	}
	
	@Override
	public String getMessage() {
		return Labels.getLabels(LabelKey.ERROR_CAR_NAME_IS_REQUIRED);
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
	
//	@Override
//	public String checkRegexPhoneNumber(String phoneNumber) {
//		if(!this.validationProperties.isPhoneNumberValid(phoneNumber)) {
//			return "SĐT không hợp lệ";
//		}
//		
//		return phoneNumber;
//	}

}
