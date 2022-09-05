package com.example.knowledge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "_outbound_req_log")
@Data
public class OutboundReqLog extends RequestLogEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6646424082979435447L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

}
