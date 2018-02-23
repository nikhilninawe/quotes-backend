package com.nikhilninawe.quotes.repository;

import com.nikhilninawe.quotes.constants.Language;
import com.nikhilninawe.quotes.model.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepo extends CrudRepository<Quote, Long> {
    Quote findByQuoteUrl(String url);
    Quote findByQuote(String q);
    List<Quote> findByLanguageAndApproved(Language language, boolean approved, Pageable pageable);
    List<Quote> findByLanguageAndApprovedAndType(Language language, boolean approved, String type, Pageable pageable);

}
