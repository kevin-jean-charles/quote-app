package com.kcode.quoteapp.controller;

import com.kcode.quoteapp.entity.Quote;
import com.kcode.quoteapp.entity.User;
import com.kcode.quoteapp.repository.QuoteRepository;
import com.kcode.quoteapp.repository.UserRepository;
import com.kcode.quoteapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/randomquotes")
    public ResponseEntity<Quote> getQuoteAtRandom() {
        Quote randQuote = quoteRepository.findAtRandom();
        return new ResponseEntity<>(randQuote, HttpStatus.OK);
    }

    @GetMapping("/quotes/users/{username}")
    public ResponseEntity<List<Quote>> getAllQuotesByUsername(@PathVariable String username) {
        try {
            User user = userRepository.findByUsername(username);
            List<Quote> quotes = quoteRepository.findAllByUserId(user.getId());

            if (quotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quotes/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable("id") long id) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);

        if (optionalQuote.isPresent()) {
            return new ResponseEntity<>(optionalQuote.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/quotes")
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        try {
            Quote createQuote = quoteRepository.save(quote);
            return new ResponseEntity<>(createQuote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/quotes/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable("id") long id, @RequestBody Quote quote) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        
        if (optionalQuote.isPresent()) {
            Quote updatedQuote = optionalQuote.get();
            updatedQuote.setUser(quote.getUser());
            updatedQuote.setAuthorname(quote.getAuthorname());
            updatedQuote.setContent(quote.getContent());
            return new ResponseEntity<>(quoteRepository.save(updatedQuote), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/quotes/{id}")
    public ResponseEntity<HttpStatus> deleteQuote(@PathVariable("id") long id) {
        try {
            quoteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
