package com.example.knowledge.repository.extend;

import java.util.List;

import com.example.knowledge.model.Role;

public interface RoleRepositoryExtend {
	List<Role> findByUserId(Long userId);
}
