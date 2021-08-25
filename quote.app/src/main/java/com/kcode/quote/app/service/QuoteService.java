package com.kcode.quote.app.service;

import com.kcode.quote.app.model.Quote;

import java.util.List;

public interface QuoteService {
    //create quote
//    void create(Long userId, Quote quote );

    //create quote with username

    void create(String username, Quote quote );

    //get quote by id
    Quote getById(Long quoteId);

    //get all quote
    List<Quote> getAll();

    //get all quote by username
    List<Quote> getAllByUsername(String username);

    //get all quote by user id
    List<Quote> getAllByUserId(Long userId);

    //update quote by id
    void update(Long quoteId, Quote quote);

    //delete quote by id
    void delete(Long quoteId);

    Quote findAtRandom();
}
