package com.solution.webfluxr2dbc.repository;

import com.solution.webfluxr2dbc.model.Payload;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface PayloadRepository extends ReactiveCrudRepository<Payload,Integer> {
}
