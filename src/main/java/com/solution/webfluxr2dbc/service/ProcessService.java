package com.solution.webfluxr2dbc.service;

import com.solution.webfluxr2dbc.model.Aggregate;
import com.solution.webfluxr2dbc.model.RequestData;
import com.solution.webfluxr2dbc.model.TransmissionStatus;
import com.solution.webfluxr2dbc.repository.PayloadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProcessService implements UseCaseProcess{

    private final String soapServiceUrl;

    private final PayloadRepository payloadRepo;

    public ProcessService(@Value("${web.service.client.url}") String soapServiceUrl, PayloadRepository repository) {
        this.soapServiceUrl = soapServiceUrl;
        this.payloadRepo = repository;
    }

    @Override
    public Mono<TransmissionStatus> sendMessage(RequestData request) {

        Mono<TransmissionStatus> status = WebClient.create()
                .post()
                .uri(soapServiceUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request.getMessage()), String.class)
                .retrieve()
                .bodyToMono(TransmissionStatus.class)
                .doOnSuccess(response -> {
                    savePayload(request);
                })
                .doOnError(Exception.class, ( Exception error ) -> {
                    System.out.println( "error : "+ error );
                    error.printStackTrace();
                });

        status.subscribe();
        return status;
    }

    @Override
    public Mono<Aggregate> findById(Integer id) {
        Mono<Aggregate> data = payloadRepo.findByRequestId(id);
        data.subscribe(
                value -> System.out.println(value.toString()),
                Throwable::printStackTrace,
                () -> System.out.println("completed without a value")
        );
        return data;
    }

    private void savePayload(RequestData dto) {
        Aggregate aggregate = new Aggregate(0L, dto.getId(), dto.getMessage());
        payloadRepo.save(aggregate).subscribe();
    }
 }
