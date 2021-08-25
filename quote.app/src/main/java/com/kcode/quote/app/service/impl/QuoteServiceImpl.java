package com.kcode.quote.app.service.impl;

import com.kcode.quote.app.exception.QuoteNotFoundException;
import com.kcode.quote.app.model.Quote;
import com.kcode.quote.app.model.UserDao;
import com.kcode.quote.app.repository.QuoteRepository;
import com.kcode.quote.app.repository.UserRepository;
import com.kcode.quote.app.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public void create(Long userId, Quote quote) {
//        Optional<UserDao> userOptional = userRepository.findById(userId);
//        if (userOptional.isEmpty()){
//            userOptional.orElseThrow(() -> new UsernameNotFoundException("Quote by id : " + userId + " not found"));
//        }
//        quote.setUser(userOptional.get());
//        quoteRepository.save(quote);
//    }

    @Override
    public void create(String username, Quote quote) {
        Optional<UserDao> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()){
            userOptional.orElseThrow(() -> new UsernameNotFoundException("Quote by id : " + username + " not found"));
        }
        quote.setUser(userOptional.get());
        quoteRepository.save(quote);
    }

    @Override
    public Quote findAtRandom() {
        return quoteRepository.findAtRandom();
    }


    @Override
    public Quote getById(Long quoteId) {
        Optional<Quote> quoteOptional = quoteRepository.findById(quoteId);
        if (quoteOptional.isEmpty()){
            quoteOptional.orElseThrow(() -> new QuoteNotFoundException("Quote by id : " + quoteId + " not found"));
        }
        return quoteOptional.get();
    }

    @Override
    public List<Quote> getAll() {
        List<Quote> quoteList = quoteRepository.findAll();
        return quoteList;
    }

    @Override
    public List<Quote> getAllByUserId(Long userId) {
        List<Quote> quoteList = quoteRepository.findByUserId(userId);
        return quoteList;
    }

    @Override
    public List<Quote> getAllByUsername(String username) {
        List<Quote> quoteList = quoteRepository.findAllByUsername(username);
        return quoteList;
    }

    //Todo : fix it
    @Override
    public void update(Long quoteId, Quote quote) {
        Quote quoteOptional = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new QuoteNotFoundException("Quote by id : " + quoteId + " not found"));
        quoteOptional.setAuthor(quote.getAuthor());
        quoteOptional.setContent(quote.getContent());
        quoteRepository.save(quoteOptional);
    }

    @Override
    public void delete(Long quoteId) {
      quoteRepository.deleteById(quoteId);
    }
}
