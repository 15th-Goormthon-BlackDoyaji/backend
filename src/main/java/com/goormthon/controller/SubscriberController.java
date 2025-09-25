package com.goormthon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriberController {

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe() {
        return ResponseEntity.ok().build();

    }
}
