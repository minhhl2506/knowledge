package com.example.knowledge.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "_role")
@Data
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436067796565410298L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "role_name", length = 50, nullable = false)
	private String roleName;
	
	@Transient
	private List<Privilege> privileges = new ArrayList<>();
	
}
