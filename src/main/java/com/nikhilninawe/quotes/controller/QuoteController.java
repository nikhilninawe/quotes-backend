package com.nikhilninawe.quotes.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.nikhilninawe.quotes.constants.Language;
import com.nikhilninawe.quotes.model.Quote;
import com.nikhilninawe.quotes.model.QuoteWrapper;
import com.nikhilninawe.quotes.repository.QuoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class QuoteController {

    @Autowired
    QuoteRepo repository;

    Map<String, String> quoteMap;

    @PostConstruct
    public void init(){
        quoteMap = new HashMap<>();
        quoteMap.put("hi", "/Users/nikhil.n/workspace/quotes/src/data/hindi.json");
        quoteMap.put("en", "/Users/nikhil.n/workspace/quotes/src/data/english.json");
        quoteMap.put("ru", "/Users/nikhil.n/workspace/quotes/src/data/russian.json");
        quoteMap.put("mr", "/Users/nikhil.n/workspace/quotes/src/data/marathi.json");
    }

    @RequestMapping(value = "/sync")
    public void syncQuotes() throws Exception {
        System.out.println("Syncing");
        final Type REVIEW_TYPE = new TypeToken<QuoteWrapper>() {
        }.getType();
        Gson gson = new Gson();
        for(String lang : quoteMap.keySet()) {
            JsonReader reader = new JsonReader(new FileReader(quoteMap.get(lang)));
            QuoteWrapper data = gson.fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
            List<Quote> quotes = data.getQuotes();
            System.out.println(quotes.size());
            for (Quote q : quotes) {
                if (Objects.isNull(q))
                    continue;
                Quote dbQuote = null;
                if (q.getType().equalsIgnoreCase("image")) {
                    dbQuote = repository.findByQuoteUrl(q.getQuoteUrl());
                } else {
                    dbQuote = repository.findByQuote(q.getQuote());
                }
                if (Objects.nonNull(dbQuote)) {
                    continue;
                }
                q.setLanguage(Language.valueOf(lang));
                q.setApproved(false);
                try {
                    repository.save(q);
                } catch (Exception ex) {
                    System.out.println(q);
                }

            }
        }
    }

    @RequestMapping(value = "/quote/{language}/{limit}/{approved}/{random}")
    public List<Quote> getQuotes(@PathVariable Language language,
                                 @PathVariable int limit,
                                 @PathVariable boolean approved,
                                 @PathVariable boolean random){
        Pageable pageable = new PageRequest(0, limit);
        return repository.findByLanguageAndApprovedAndType(language, approved, "image", pageable);
    }
}
