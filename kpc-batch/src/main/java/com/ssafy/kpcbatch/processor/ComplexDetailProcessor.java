package com.ssafy.kpcbatch.processor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailsDto;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexPyeongDetailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Slf4j
public class ComplexDetailProcessor implements ItemProcessor<Long, ComplexDetailsDto> {
    private final RestTemplate restTemplate;
    private final String apiUrl;
    public ComplexDetailProcessor(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }
    @Override
    public ComplexDetailsDto process(Long item) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2OTk5MjY3OTUsImV4cCI6MTY5OTkzNzU5NX0.rnemVzmmKdbPVI3XhnXuq4vNv_30_8R6vcMkkOavRrI");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl+"/"+String.valueOf(item));

        log.info("Fetching region data from an external API by using the url: {}", uriBuilder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ComplexDetailsDto complexDetailsDto = objectMapper.readValue(response.getBody(), ComplexDetailsDto.class);
        String complexNo = complexDetailsDto.getComplexDetail().getComplexNo();
        List<ComplexPyeongDetailDto> ls = complexDetailsDto.getComplexPyeongDetailList();
        ls.forEach(complexPyeongDetailDto -> {
            complexPyeongDetailDto.setComplexNo(complexNo);
            if (complexPyeongDetailDto.getArticleStatistics() != null)
            complexPyeongDetailDto.getArticleStatistics().setComplexNo(complexNo);
            if (complexPyeongDetailDto.getLandPriceMaxByPtp() != null)
            complexPyeongDetailDto.getLandPriceMaxByPtp().setComplexNo(complexNo);
        });
        log.info("receivedData: {}", complexDetailsDto);
        return complexDetailsDto;
    }
}
