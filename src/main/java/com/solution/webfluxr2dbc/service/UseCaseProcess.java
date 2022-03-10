package com.solution.webfluxr2dbc.service;

import com.solution.webfluxr2dbc.model.Aggregate;
import com.solution.webfluxr2dbc.model.RequestData;
import com.solution.webfluxr2dbc.model.TransmissionStatus;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface UseCaseProcess {
    Mono<TransmissionStatus> sendMessage(RequestData request) throws IOException;
    Mono<Aggregate> findById(Integer id);
}
