package com.example.knowledge.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Pageable;

import com.example.knowledge.enums.EntityStatus;
import com.example.knowledge.model.Car;
import com.example.knowledge.model.ResultSet;
import com.example.knowledge.repository.extend.CarRepositoryExtend;
import com.example.knowledge.util.QueryUtils;
import com.example.knowledge.util.Validator;

public class CarRepositoryImpl implements CarRepositoryExtend {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long count(String keyword) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT COUNT(1) FROM Car e ");

		Map<String, Object> values = new HashMap<>();

		sql.append(this.createWhereQuery(keyword, values));

		javax.persistence.Query query = this.entityManager.createQuery(sql.toString(), Long.class);

		values.forEach(query::setParameter);

		return (Long) query.getSingleResult();
	}

	@Override
	public List<Car> search(String keyword, Pageable pageable) {

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
		
		sql.append(" WHERE e.price > 0 AND e.status <> :statusDelete");
		
		values.put("statusDelete", EntityStatus.DELETED.getStatus());

		if (Validator.isNotNull(keyword)) {
			sql.append(" AND e.name LIKE :keyword ");
			values.put("keyword", QueryUtils.addFullQueryParam(keyword));
		}
		
		sql.append(" ORDER BY e.lastModifiedDate DESC ");

		return sql.toString();
	}
	
	@Override
	public ResultSet<Car> searchByKeyword(String keyword, Pageable pageable) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);

		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Car.class)
                .get();
        
        BooleanJunction<?> mustJunc = queryBuilder.bool();
        
        mustJunc = mustJunc.must(queryBuilder
                .range()
                .onField(Car.FieldName.PRICE)
                .from(0).to(50000000).createQuery());
        
        org.apache.lucene.search.Query query = mustJunc.createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Car.class);
        
//        SortFieldContext sortFieldContext = queryBuilder.sort()
//                .byScore().desc()
//                .andByField("lastModifiedDate").desc();
//        
//        Sort sort = sortFieldContext.createSort();
//        
//        jpaQuery.setSort(sort);
        
        int count = jpaQuery.getResultSize();

        jpaQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        jpaQuery.setMaxResults(pageable.getPageSize());

        return new ResultSet<>(jpaQuery.getResultList(), count);
        
	}

}
