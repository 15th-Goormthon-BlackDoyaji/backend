package com.goormthon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Entity(name = "user_card")
@NoArgsConstructor
@AllArgsConstructor
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subscriber_id")
    private long subscriberId;

    @Column(name = "info_id")
    private long infoId;

    private LocalDate localDate;

    public UserCard(long subscriberId, long infoId) {
        this(null, subscriberId, infoId, LocalDate.now(ZoneId.of("Asia/Seoul")));
    }
}
