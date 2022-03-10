package com.solution.webfluxr2dbc.controller;

import com.solution.webfluxr2dbc.model.TransmissionStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockWebService {
    @PostMapping("/test_soap/endpoint1")
    private TransmissionStatus getMessage(@RequestBody String msg) {
        return new TransmissionStatus("200", msg);
    }
}
