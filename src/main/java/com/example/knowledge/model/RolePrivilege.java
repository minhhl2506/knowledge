package com.example.knowledge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_role_privilege")
@IdClass(RolePrivilegeId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePrivilege {
	
	@Id
	@Column(name = "role_id")
	private long roleId;
	
	@Id
	@Column(name = "privilege_id")
	private long privilegeId;
}
