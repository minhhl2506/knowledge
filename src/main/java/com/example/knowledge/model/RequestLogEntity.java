package com.example.knowledge.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class RequestLogEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7404601176101518378L;
	
	@Column(name = "request_uri", length = 255)
	private String requestUri;
	
	@Column(name = "method", length = 255)
	private String method;
	
	@Lob
	@Column(name = "request_data", length = 255)
	private String requestData;
	
	@Column(name = "request_time")
	private Instant requestTime;
	
	@Column(name = "service_name", length = 500)
	private String serviceName;
}
