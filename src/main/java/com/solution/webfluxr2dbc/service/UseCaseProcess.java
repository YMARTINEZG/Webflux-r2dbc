package com.solution.webfluxr2dbc.service;

import com.solution.webfluxr2dbc.model.Aggregate;
import com.solution.webfluxr2dbc.model.RequestData;
import reactor.core.publisher.Mono;

public interface UseCaseProcess {
    Mono<String> sendMessage(String message);
    Mono<Aggregate> findById(Integer id);
    void savePayload(RequestData payload);
}
