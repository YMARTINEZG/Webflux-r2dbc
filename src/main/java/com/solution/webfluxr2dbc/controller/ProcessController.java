package com.solution.webfluxr2dbc.controller;

import com.solution.webfluxr2dbc.model.Aggregate;
import com.solution.webfluxr2dbc.model.RequestData;
import com.solution.webfluxr2dbc.model.TransmissionStatus;
import com.solution.webfluxr2dbc.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@Slf4j
public class ProcessController {

    @Autowired
    ProcessService processService;
    @RequestMapping(value = { "/test", "/" },method = RequestMethod.POST)
    public Mono<ResponseEntity<?>> processPayload(@RequestBody RequestData request) throws IOException {

        Mono<TransmissionStatus> e = processService.sendMessage(request);
        return e.map(t ->
        {
            if (t.getStatus().equals("200")) {
                //TODO with TransmissionStatus.getMessage() get id from ProcessContext and include into ResponseBody
                return new ResponseEntity<>(request.getId(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(request.getId(), HttpStatus.BAD_REQUEST);
            }
        });
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<Aggregate> findById(@PathVariable("id") Integer id) {
        return processService.findById(id);
    }

}
