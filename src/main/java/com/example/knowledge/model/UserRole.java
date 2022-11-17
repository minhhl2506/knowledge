package com.example.knowledge.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "_user_role")
@Data
@IdClass(UserRoleId.class)
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4277434244767679823L;
	
	@Id
	@Column(name = "user_id")
	private long userId;

	@Id
	@Column(name = "role_id")
	private long roleId;
	
}
