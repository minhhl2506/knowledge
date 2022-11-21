package com.example.knowledge.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.knowledge.model.Privilege;
import com.example.knowledge.repository.extend.PrivilegeRepositoryExtend;

public class PrivilegeRepositoryImpl implements PrivilegeRepositoryExtend {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> findByRoleId(Long roleId) {
		StringBuilder sql = new StringBuilder(1);

		sql.append("SELECT e FROM Privilege e, RolePrivilege rp WHERE e.id = rp.privilegeId AND rp.roleId = :roleId");

		Query query = this.entityManager.createQuery(sql.toString(), Privilege.class);

		query.setParameter("roleId", roleId);

		return query.getResultList();
	}
}
