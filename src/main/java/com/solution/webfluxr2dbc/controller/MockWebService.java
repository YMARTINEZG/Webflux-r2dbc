package com.solution.webfluxr2dbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockWebService {
    @GetMapping("/test_soap/endpoint1")
    private String getMessage() {
        return "OK";
    }
}
