package com.example.knowledge.repository.extend;

import java.util.List;

import com.example.knowledge.model.Privilege;

public interface PrivilegeRepositoryExtend {
	List<Privilege> findByRoleId(Long roleId);
}
