package com.goormthon.controller;

import com.goormthon.domain.Subscriber;
import com.goormthon.dto.request.SubscribeRequest;
import com.goormthon.dto.response.SubscribeResponse;
import com.goormthon.repository.SubscriberRepository;
import com.goormthon.service.SubscriberService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscriberController {

    private final SubscriberService subscriberService;

    @PostMapping("/subscribe")
    public ResponseEntity<SubscribeResponse> subscribe(
            @Valid @RequestBody SubscribeRequest request
    ) {
        Subscriber savedSubscriber = subscriberService.subscribe(request);
        SubscribeResponse response = new SubscribeResponse(savedSubscriber);
        return ResponseEntity.created(URI.create("/subscribe/"+ response.userId()))
                .body(response);
    }
}
