package com.quotely;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Properties;

public class QuoteServiceForismaticImpl implements QuoteService{

    @Override
    public String getQuote(String lang){
        Quote quote = new Quote();
        try (HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build()) {

            String url = buildURL(lang);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            quote = mapper.readValue(response.body(), Quote.class);
        } catch (IOException e) {
            System.out.println("IOException: " + e); // this should be changed to logger
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: " + e);
        }
        return quote.toString();
    }


        static String buildURL(String langArg) {
            final String FORMAT_PARAM = "format=";
            final String AMP = "&";
            final String LANG_PARAM = "lang=";
            final String EN_PARAM = "en";
            final String RU_PARAM = "ru";

            Properties props = new QuotelyProperties().getProps();
            // this can be configured by environment into seperate files or externalize into a cloud
            String baseURL =  props.getProperty("req.url.base");
            String method =  props.getProperty("req.api.method");
            String lang = (langArg.toLowerCase().startsWith(EN_PARAM)) ? EN_PARAM : RU_PARAM;
            String format =  props.getProperty("req.format");

            return baseURL + AMP + method + AMP + LANG_PARAM + lang + AMP + FORMAT_PARAM + format;
        }
}
