package com.kcode.quote.app.controller;

import com.kcode.quote.app.model.Quote;
import com.kcode.quote.app.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/quotes")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    /**
     * get quote at random
     * @return
     */
    @GetMapping("/random")
    public ResponseEntity<Quote> getQuoteAtRandom() {
        Quote randQuote = quoteService.findAtRandom();
        return new ResponseEntity<>(randQuote, HttpStatus.OK);
    }

    /**
     * get quote by user id
     * @param userId
     * @return
     */
//    @GetMapping("/users/{userId}")
//    public ResponseEntity <List<Quote>> getQuotesByUserId(@PathVariable Long userId) {
//        List<Quote> quoteList = quoteService.getAllByUserId(userId);
//        return new ResponseEntity<>(quoteList, HttpStatus.OK);
//    }


    @GetMapping("/users/{username}")
    public ResponseEntity <List<Quote>> getQuotesByUsername(@PathVariable String username) {
        List<Quote> quoteList = quoteService.getAllByUsername(username);
        return new ResponseEntity<>(quoteList, HttpStatus.OK);
    }

    /**
     * get quote by id
     * @param quoteId
     * @return
     */
    @GetMapping("/{quoteId}")
    public ResponseEntity <Quote>getQuoteById(@PathVariable Long quoteId) {
        Quote quote = quoteService.getById(quoteId);
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    /**
     * create quote
     * @param quote
     * @return
     */
    @PostMapping("/users/{username}")
    public ResponseEntity<Quote> createQuote(@PathVariable String username,
                                             @RequestBody Quote quote) {
            quoteService.create(username, quote);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * update quote by id
     * Find the quote to update by id
     * Set new Quote
     * Set new Author
     * Save new quote
     * @param quoteId
     * @param quote
     * @return
     */
    @PutMapping("/{quoteId}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long quoteId,
                                             @RequestBody @Valid Quote quote) {
        quoteService.update(quoteId, quote);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * delete quote by id
     * @param quoteId
     * @return
     */
    @DeleteMapping("/{quoteId}")
    public ResponseEntity<Quote> deleteQuote(@PathVariable Long quoteId) {
        quoteService.delete(quoteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
