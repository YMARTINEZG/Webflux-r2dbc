package com.solution.webfluxr2dbc.controller;

import com.solution.webfluxr2dbc.model.Aggregate;
import com.solution.webfluxr2dbc.model.RequestData;
import com.solution.webfluxr2dbc.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ProcessController {

    @Autowired
    ProcessService processService;

    @RequestMapping(value = { "/test", "/" },method = RequestMethod.POST)

    public void processPayload(@RequestBody RequestData request) {
        Mono<String> e = processService.sendMessage(request.getMessage());
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        if(status.equals(HttpStatus.OK)){
            processService.savePayload(request);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<Aggregate> findById(@PathVariable("id") Integer id) {
        return processService.findById(id);
//        e.subscribe(res ->
//                log.info(String.valueOf(res.getRequestMessage())
//                ));
    }

}
