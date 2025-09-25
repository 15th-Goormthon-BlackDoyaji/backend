package com.goormthon.repository;

import com.goormthon.domain.Subscriber;
import com.goormthon.exception.custom.GroomServerErrorException;
import com.goormthon.exception.errorcode.ServerErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    default Subscriber getSubscriber(long id) {
        return findById(id)
                .orElseThrow(() -> new GroomServerErrorException(ServerErrorCode.INVALID_USER_FOUND));
    }

}
