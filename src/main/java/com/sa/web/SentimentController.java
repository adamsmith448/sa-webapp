package com.sa.web;

import com.sa.web.dto.SentenceDto;
import com.sa.web.dto.SentimentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

@RestController
public class SentimentController {

    @Value("${sa.logic.api.url}")
    private String saLogicApiUrl;

    @Value("${sa.frontend.url}")
    private String saFrontendUrl;

    @PostMapping("/sentiment")
    public SentimentDto sentimentAnalysis(@RequestBody SentenceDto sentenceDto, final HttpServletResponse response) {
        RestTemplate restTemplate = new RestTemplate();
        response.setHeader("Access-Control-Allow-Origin", saFrontendUrl);
        return restTemplate.postForEntity(saLogicApiUrl + "/analyse/sentiment",
                sentenceDto, SentimentDto.class)
                .getBody();
    }

    @GetMapping("/testHealth")
    public void testHealth() {
    }
}


