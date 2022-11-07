package com.example.knowledge.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;

import com.example.knowledge.model.Car;
import com.example.knowledge.repository.extend.CarRepositoryExtend;
import com.example.knowledge.util.QueryUtils;
import com.example.knowledge.util.Validator;

@Transactional
public class CarRepositoryImpl implements CarRepositoryExtend {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long count(String keyword) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT COUNT(1) FROM Car e ");

		Map<String, Object> values = new HashMap<>();

		sql.append(this.createWhereQuery(keyword, values));

		Query query = this.entityManager.createQuery(sql.toString(), Long.class);

		values.forEach(query::setParameter);

		return (Long) query.getSingleResult();
	}

	@Override
	public List<Car> searchByKeyWord(String keyword, Pageable pageable) {

		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT e FROM Car e ");

		Map<String, Object> values = new HashMap<>();

		sql.append(createWhereQuery(keyword, values));

		javax.persistence.Query query = entityManager.createQuery(sql.toString(), Car.class);

		values.forEach(query::setParameter);

		if (pageable != null) {
			query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());

			query.setMaxResults(pageable.getPageSize());
		}

		return query.getResultList();
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
//		
//		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory() 
//				  .buildQueryBuilder()
//				  .forEntity(Car.class)
//				  .get();
//		
//		Query query = queryBuilder
//				  .keyword()
//				  .onField("name")
//				  .matching(keyword)
//				  .createQuery();
//		
//		FullTextQuery fullTextQuery
//		  = fullTextEntityManager.createFullTextQuery(query, Car.class);
//		
//		return fullTextQuery.getResultList();
	}

	private String createWhereQuery(String keyword, Map<String, Object> values) {
		StringBuilder sql = new StringBuilder();

		if (Validator.isNotNull(keyword)) {
			sql.append(" WHERE e.name LIKE :keyword ");
			values.put("keyword", QueryUtils.addFullQueryParam(keyword));
		}

		return sql.toString();
	}

}
