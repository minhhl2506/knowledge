package com.example.knowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knowledge.model.Role;
import com.example.knowledge.repository.extend.RoleRepositoryExtend;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryExtend {

}
