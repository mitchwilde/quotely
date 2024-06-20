import static org.junit.jupiter.api.Assertions.assertEquals;

import com.quotely.QuotelyProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Properties;

import com.quotely.Quote;

public class QuotelyTests {
    private static final String TEST_URL_PROP = "test.url.eng";
    public static final int STATUS_CODE_SUCCESS = 200;

    @Test
    public void serializeDeserializeTest() throws IOException {
        Quote quote = new Quote("Don't trust everything you read on the internet","Abraham Lincoln");
        ObjectMapper mapper = new ObjectMapper();

        String jsonStr = mapper.writeValueAsString(quote);
        Quote result = mapper.readValue(jsonStr, Quote.class);
        assertEquals(quote.getQuoteText(),result.getQuoteText());
        System.out.println("serializeDeserializeTest succeeded!");
    }

    @Test
    public void forismaticUpTest() throws IOException, InterruptedException {
        Properties testProps = new QuotelyProperties().getProps();
        String testURL = testProps.getProperty(TEST_URL_PROP);
        try (HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(testURL))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(response.statusCode(), STATUS_CODE_SUCCESS);
        }
    }
}
