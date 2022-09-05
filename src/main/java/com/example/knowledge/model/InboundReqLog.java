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
@Table(name = "_inbound_req_log")
@Data
public class InboundReqLog extends RequestLogEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4066771278664136827L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

}
