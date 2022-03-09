package com.solution.webfluxr2dbc.service;

import com.solution.webfluxr2dbc.model.Aggregate;
import com.solution.webfluxr2dbc.model.RequestData;
import com.solution.webfluxr2dbc.repository.PayloadRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProcessService implements UseCaseProcess{

    private static final int DEFAULT_PORT = 8080;

    @Setter
    private int serverPort = DEFAULT_PORT;

    @Autowired
    PayloadRepository payloadRepo;

    @Override
    public Mono<String> sendMessage(String message) {
        Mono<String> messageMono = WebClient.create()
                .get()
                .uri(getMockServiceUri())
                .retrieve()
                .bodyToMono(String.class);

        messageMono.subscribe(log::info);
        return messageMono;
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

    @Override
    public void savePayload(RequestData dto) {
        Aggregate aggregate = new Aggregate(0L, dto.getId(), dto.getMessage());
        payloadRepo.save(aggregate).subscribe();
    }
    private String getMockServiceUri() {
        return "http://localhost:" + serverPort + "/test_soap/endpoint1";
    }
}
