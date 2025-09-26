package com.goormthon.repository;

import com.goormthon.domain.UserCard;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {

    @Query("""
        select user_card
          from UserCard as user_card
        where user_card.createdAt > :date and user_card.subscriberId = :subscriber_id
    """)
    List<UserCard> findAllByCondition(
            @Param("subscriber_id") long subscriber_id,
            @Param("date") LocalDate date
    );
}
