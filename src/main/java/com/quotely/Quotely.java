package com.quotely;

import java.util.logging.Logger;

/**
 * Quotely will fetch a random quote in either English or Russian from
 * the Forismatic API @ <a href="https://api.forismatic.com/api/1.0/">Forismatic API</a>
 */
public class Quotely {
    private static final Logger logger = Logger.getLogger("QuotelyLogger");
    private static final String DEFAULT_LANGUAGE = "English";
    private static final String RUSSIAN_LANG = "Russian";

    public static void main(String[] args) {
        String language = DEFAULT_LANGUAGE;
        if (args.length > 0) {
           if (args[0].equalsIgnoreCase(RUSSIAN_LANG)){
               language = "ru";
           }
        }
        process(language);
    }

    /**
      // Add Java docs
    */
    static void process(String language){
        QuoteService service = new QuoteServiceForismaticImpl(); // use factory design pattern or AOP (ex. Spring framework)
        System.out.println();
        System.out.println(service.getQuote(language));
    }
}

