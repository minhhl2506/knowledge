package com.example.knowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knowledge.model.Privilege;
import com.example.knowledge.repository.extend.PrivilegeRepositoryExtend;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>, PrivilegeRepositoryExtend {

}
