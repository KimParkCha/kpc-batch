package com.ssafy.kpcbatch.reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.kpcbatch.dto.RegionDto;
import com.ssafy.kpcbatch.dto.RegionListDto;
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
    private int regionIndex;
    public RealEstateRegionReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        log.info("Reading the information of the region data");

        if (regionDataIsNotInitialized()) { // 초기 데이터가 없다면 호출
            regionDtos = fetchRegionDataFromAPI();
        }

        RegionDto nextRegion = null;

        if (regionIndex < regionDtos.size()) {
            nextRegion = regionDtos.get(regionIndex);
            regionIndex += 1;
            if (regionIndex > regionDtos.size()) {
                return null;
            }
        }
        return nextRegion;
    }
    boolean regionDataIsNotInitialized() {
        return regionDtos == null;
    }

    private List<RegionDto> fetchRegionDataFromAPI() throws JsonProcessingException {
        List<RegionDto> ls = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("cortarNo", "0000000000");

        log.info("Fetching region data from an external API by using the url: {}", uriBuilder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        // Json parsing 하는 부분... 근데 이게 최선인가 싶네
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RegionListDto regionListDto = objectMapper.readValue(response.getBody(), RegionListDto.class);

//        log.info("receive 시 Data : {}", regionListDto.getRegionList());
        for (RegionDto region: regionListDto.getRegionList()) {
            ls.add(region);
            uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("cortarNo", region.getCortarNo());
            response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
                    new HttpEntity<>(headers), String.class);
            RegionListDto regionListDto2 = objectMapper.readValue(response.getBody(), RegionListDto.class);
//            log.info("receive 군 Data : {}", regionListDto2.getRegionList());
            for (RegionDto region2 : regionListDto2.getRegionList()) {
                ls.add(region2);
                uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                        .queryParam("cortarNo", region2.getCortarNo());
                response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
                        new HttpEntity<>(headers), String.class);
                RegionListDto regionListDto3 = objectMapper.readValue(response.getBody(), RegionListDto.class);
//                log.info("receive 구 Data : {}", regionListDto3.getRegionList());
                ls.addAll(regionListDto3.getRegionList());

            }
        }
        return ls;
    }

}