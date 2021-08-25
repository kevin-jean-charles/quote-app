package com.kcode.quote.app.repository;

import com.kcode.quote.app.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(value = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findAtRandom();

    @Query(value = "SELECT * FROM quotes WHERE user_quote = ?1", nativeQuery = true)
    List<Quote> findByUserId(Long userId);

    @Query(value = "SELECT * FROM quotes WHERE quote_has_username = ?1", nativeQuery = true)
    List<Quote> findAllByUsername(String username);

}
