package com.nikhilninawe.quotes.repository;

import com.nikhilninawe.quotes.model.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepo extends CrudRepository<Quote, Long> {

}
