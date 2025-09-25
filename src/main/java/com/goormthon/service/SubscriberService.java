package com.goormthon.service;

import com.goormthon.domain.Subscriber;
import com.goormthon.dto.request.SubscribeRequest;
import com.goormthon.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final SubscriberRepository repository;

    public Subscriber subscribe(SubscribeRequest request) {
        Subscriber subscriber = request.toSubscriber();
        return repository.save(subscriber);
    }
}
