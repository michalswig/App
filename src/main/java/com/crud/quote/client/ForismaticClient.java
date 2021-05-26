package com.crud.quote.client;

import com.crud.quote.domain.ForismaticQuoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ForismaticClient {
    private final RestTemplate restTemplate;

    public List<ForismaticQuoteDto> getForismaticQuote(){

        ForismaticQuoteDto[] forismaticReponse =
                restTemplate.getForObject("https://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=jsonp&jsonp=?",
                        ForismaticQuoteDto[].class
                );
        return Collections.emptyList();
    }


}
