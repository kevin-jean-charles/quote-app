package com.kcode.quoteapp.repository;

import com.kcode.quoteapp.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(value = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findAtRandom();

    @Query(value = "SELECT * FROM quotes WHERE user_quote = ?1", nativeQuery = true)
    List<Quote> findAllByUserId(Long userId);
}
