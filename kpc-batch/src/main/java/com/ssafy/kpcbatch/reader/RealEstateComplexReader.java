package com.ssafy.kpcbatch.reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.kpcbatch.dto.ComplexDto;
import com.ssafy.kpcbatch.dto.ComplexListDto;
import com.ssafy.kpcbatch.entity.Complex;
import com.ssafy.kpcbatch.entity.Region;
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
public class RealEstateComplexReader implements ItemProcessor<Region, List<Complex>> {
    private final RestTemplate restTemplate;
    private final String apiUrl;
    public RealEstateComplexReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Complex> process(Region item) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("cortarNo", item.getCortarNo())
                .queryParam("realEstateType", "APT")
                .queryParam("order","");

        log.info("Fetching region data from an external API by using the url: {}", uriBuilder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        // Json parsing 하는 부분... 근데 이게 최선인가 싶네
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ComplexListDto complexListDto = objectMapper.readValue(response.getBody(), ComplexListDto.class);
        List<Complex> list = new ArrayList<>();
        for (ComplexDto complexDto: complexListDto.getComplexList()) {
            Complex complex = Complex.builder()
                    .complexNo(complexDto.getComplexNo())
                    .complexName(complexDto.getComplexName())
                    .cortarNo(complexDto.getCortarNo())
                    .realEstateTypeCode(complexDto.getRealEstateTypeCode())
                    .realEstateTypeName(complexDto.getRealEstateTypeName())
                    .detailAddress(complexDto.getDetailAddress())
                    .latitude(complexDto.getLatitude())
                    .longitude(complexDto.getLongitude())
                    .totalHouseholdCount(complexDto.getTotalHouseholdCount())
                    .totalBuildingCount(complexDto.getTotalBuildingCount())
                    .highFloor(complexDto.getHighFloor())
                    .lowFloor(complexDto.getLowFloor())
                    .useApproveYmd(complexDto.getUseApproveYmd())
                    .dealCount(complexDto.getDealCount())
                    .leaseCount(complexDto.getLeaseCount())
                    .rentCount(complexDto.getRentCount())
                    .shirtTermRentCount(complexDto.getShirtTermRentCount())
                    .cortarAddress(complexDto.getCortarAddress())
                    .build();
            list.add(complex);
        }
        return list;
    }
}
