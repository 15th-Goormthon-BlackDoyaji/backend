package com.goormthon.repository;

import com.goormthon.domain.UserCard;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {

    @Query("""
    select userCard
    from UserCard userCard
        where userCard.createAt > :date
    """)
    List<UserCard> findAllByAfterDate(LocalDate date);
}
