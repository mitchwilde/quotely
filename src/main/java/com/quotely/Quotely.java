package com.quotely;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Quotely will fetch a random quote in either English or Russian from
 * the Forismatic API @ <a href="https://api.forismatic.com/api/1.0/">Forismatic API</a>
 */
public class Quotely {
    private static final String ENGLISH_LANG = "english";
    private static final String RUSSIAN_LANG = "russian";

    public static void main(String[] args) {
        if (validInput(args)) {
            try (HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .build()) {

                String url = buildURL(args[0]);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .POST(HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                ObjectMapper mapper = new ObjectMapper();
                Quote quote = mapper.readValue(response.body(), Quote.class);
                System.out.println();
                System.out.println(quote.toString());
            } catch (IOException e) {
                System.out.println("IOException: " + e);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e);
            }
        } else {
            System.out.println("Usage: java -jar quotely-[version].jar <language>(\"English\" OR \"Russian\")");
        }
    }

    static boolean validInput(String[] args) {
        return (args.length > 0 && (ENGLISH_LANG.equalsIgnoreCase(args[0]) || RUSSIAN_LANG.equalsIgnoreCase(args[0])));
    }

    static String buildURL(String langArg) {
        final String FORMAT_PARAM = "format=";
        final String AMP = "&";
        final String LANG_PARAM = "lang=";
        final String EN_PARAM = "en";
        final String RU_PARAM = "ru";

        Properties props = QuotelyProperties.getProps();
        String baseURL =  props.getProperty("req.url.base");
        String method =  props.getProperty("req.api.method");
        String lang = (langArg.toLowerCase().startsWith(EN_PARAM)) ? EN_PARAM : RU_PARAM;
        String format =  props.getProperty("req.format");

        return baseURL + AMP + method + AMP + LANG_PARAM + lang + AMP + FORMAT_PARAM + format;
    }
}

