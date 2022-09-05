package com.example.knowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.knowledge.model.InboundReqLog;

@Repository
public interface InboundRequestLogRepository extends JpaRepository<InboundReqLog, Long> {

}
