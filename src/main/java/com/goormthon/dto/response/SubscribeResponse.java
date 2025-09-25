package com.goormthon.dto.response;

import com.goormthon.domain.Subscriber;

public record SubscribeResponse(
        long userId
) {

    public SubscribeResponse(Subscriber subscriber) {
        this(subscriber.getId());
    }
}
