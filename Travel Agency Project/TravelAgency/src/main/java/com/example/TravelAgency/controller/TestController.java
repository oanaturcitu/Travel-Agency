package com.example.TravelAgency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/controller")
public class TestController {
    @GetMapping
    public String getForTestController() {
        return "Testul a fost un succes! Felicitari!";
    }
}
