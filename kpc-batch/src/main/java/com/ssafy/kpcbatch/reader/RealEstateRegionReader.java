package com.ssafy.kpcbatch.reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.kpcbatch.dto.RegionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class RealEstateRegionReader implements ItemReader {
    private final String apiUrl;
    private final RestTemplate restTemplate;
    private List<RegionDto> regionDtos;

    public RealEstateRegionReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("Reading the information of the next book");

        regionDtos = fetchRegionDataFromAPI(5000000000l);

        return regionDtos;
    }

    private List<RegionDto> fetchRegionDataFromAPI(Long cortarNo) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("cortarNo", cortarNo);

        log.info("Fetching book data from an external API by using the url: {}", uriBuilder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        // Json parsing 하는 부분... 근데 이게 최선인가 싶네
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseObject = objectMapper.readValue(response.getBody(),
                new TypeReference<Map<String, Object>>() {});
        log.info("receive Data : {}", responseObject.toString());
        ArrayList<RegionDto> regionDtoList = (ArrayList<RegionDto>) responseObject.get("regionList");
        return regionDtoList;
    }

}