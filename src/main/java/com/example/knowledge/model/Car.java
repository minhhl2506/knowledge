package com.example.knowledge.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_car")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Indexed
@Document(indexName = "blog")
public class Car implements Serializable {

	/** The Constant serialVersionUID */
	private static final long serialVersionUID = 3752466100126131064L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Field
	@Column(name = "name", length = 255)
	private String name;
	
	@Field
	@Column(name = "price", length = 255)
	private int price;
}
