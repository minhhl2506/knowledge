package com.example.knowledge.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.knowledge.model.Role;
import com.example.knowledge.repository.extend.RoleRepositoryExtend;

public class RoleRepositoryImpl implements RoleRepositoryExtend {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByUserId(Long userId) {
		StringBuilder sql = new StringBuilder(1);

		sql.append("SELECT e FROM Role e INNER JOIN UserRole ur ON e.id = ur.roleId WHERE ur.userId = :userId");

		Query query = this.entityManager.createQuery(sql.toString(), Role.class);

		query.setParameter("userId", userId);

		return query.getResultList();
	}
}
