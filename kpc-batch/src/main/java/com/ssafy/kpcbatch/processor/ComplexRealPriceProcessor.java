package com.ssafy.kpcbatch.processor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.kpcbatch.dto.realPrice.RealPriceOnMonthDto;
import com.ssafy.kpcbatch.dto.realPrice.RealPricesDto;
import com.ssafy.kpcbatch.entity.complex.Complex;
import com.ssafy.kpcbatch.entity.realPrice.RealPrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ComplexRealPriceProcessor implements ItemProcessor<Long, List<RealPrice>> {
    private final RestTemplate restTemplate;
    private final String apiUrl;
    public ComplexRealPriceProcessor(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }
    @Override
    public List<RealPrice> process(Long item) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2OTk5MjY3OTUsImV4cCI6MTY5OTkzNzU5NX0.rnemVzmmKdbPVI3XhnXuq4vNv_30_8R6vcMkkOavRrI");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl+"/"+item+"/prices/real");
        uriBuilder.queryParam("complexNo", item);
        uriBuilder.queryParam("type","table");
        log.info("Fetching region data from an external API by using the url: {}", uriBuilder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RealPricesDto realPricesDto = objectMapper.readValue(response.getBody(), RealPricesDto.class);

        List<RealPrice> list = new ArrayList<>();
        realPricesDto.getRealPriceOnMonthList().forEach(realPriceOnMonthDto -> {
            realPriceOnMonthDto.getRealPriceList().forEach(realPriceDto -> {
                RealPrice realPrice = RealPrice.builder()
                        .complexNo(item)
                        .tradeYear(realPriceDto.getTradeYear())
                        .tradeMonth(realPriceDto.getTradeMonth())
                        .tradeType(realPriceDto.getTradeType())
                        .dealPrice(realPriceDto.getDealPrice())
                        .floor(realPriceDto.getFloor())
                        .representativeArea(realPriceDto.getRepresentativeArea())
                        .exclusiveArea(realPriceDto.getExclusiveArea())
                        .formattedPricce(realPriceDto.getFormattedPricce())
                        .formattedTradeYearMonth(realPriceDto.getFormattedTradeYearMonth())
                        .build();
                list.add(realPrice);
            });
        });
        return list;
    }
}
