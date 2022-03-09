package com.solution.webfluxr2dbc.repository;

import com.solution.webfluxr2dbc.model.Aggregate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PayloadRepository extends ReactiveCrudRepository<Aggregate,Long> {
    Mono<Aggregate> findByRequestId(Integer id);
}
