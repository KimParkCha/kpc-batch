package com.ssafy.kpcbatch.processor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailsDto;
import com.ssafy.kpcbatch.dto.property.PropertiesDto;
import com.ssafy.kpcbatch.entity.property.Article;
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
public class ComplexPropertyProcessor implements ItemProcessor<Long, List<Article>> {
    private final RestTemplate restTemplate;
    private final String apiUrl;
    public ComplexPropertyProcessor(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Article> process(Long item) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Host", "new.land.naver.com");
        headers.set("Referer", "https://new.land.naver.com/...");
        headers.set("sec-ch-ua", "\".Not\\/A)Brand\";v=\"99\", \"Google Chrome\";v=\"103\", \"Chromium\";v=\"103\"");
        headers.set("sec-ch-ua-mobile", "?0");
        headers.set("sec-ch-ua-platform", "macOS");
        headers.set("Sec-Fetch-Dest", "empty");
        headers.set("Sec-Fetch-Mode", "cors");
        headers.set("Sec-Fetch-Site", "same-origin");
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IlJFQUxFU1RBVEUiLCJpYXQiOjE2OTk5MjY3OTUsImV4cCI6MTY5OTkzNzU5NX0.rnemVzmmKdbPVI3XhnXuq4vNv_30_8R6vcMkkOavRrI");
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("https://new.land.naver.com/api/articles/complex/"+item);
        uriBuilder.queryParam("realEstateType","APT");
        uriBuilder.queryParam("tradeType","");
        uriBuilder.queryParam("tag","APT");
        uriBuilder.queryParam("realEstateType","%3A%3A%3A%3A%3A%3A%3A%3A");
        uriBuilder.queryParam("rentPriceMin",0);
        uriBuilder.queryParam("rentPriceMax",900000000);
        uriBuilder.queryParam("priceMin",0);
        uriBuilder.queryParam("priceMax",900000000);
        uriBuilder.queryParam("areaMin",0);
        uriBuilder.queryParam("areaMax",900000000);
        uriBuilder.queryParam("oldBuildYears","");
        uriBuilder.queryParam("recentlyBuildYears","");
        uriBuilder.queryParam("minHouseHoldCount","");
        uriBuilder.queryParam("maxHouseHoldCount","");
        uriBuilder.queryParam("sameAddressGroup",false);
        uriBuilder.queryParam("showArticle",false);
        uriBuilder.queryParam("minMaintenanceCost","");
        uriBuilder.queryParam("maxMaintenanceCost","");
        uriBuilder.queryParam("priceType","RETAIL");
        uriBuilder.queryParam("directions","");
        uriBuilder.queryParam("page",1);
        uriBuilder.queryParam("complexNo",108405);
        uriBuilder.queryParam("buildingNos","");
        uriBuilder.queryParam("areaNos","");
        uriBuilder.queryParam("type","list");
        uriBuilder.queryParam("order","dateDesc");

        log.info("Fetching region data from an external API by using the url: {}", uriBuilder.toUriString());

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Article> ls = new ArrayList<>();

        try {
            PropertiesDto propertiesDto = objectMapper.readValue(response.getBody(), PropertiesDto.class);

            log.info("receivedData: {}", propertiesDto);

            propertiesDto.getArticleList().forEach(articleDto -> {
                Article article = Article.builder()
                        .complexNo(item)
                        .articleNo(articleDto.getArticleNo())
                        .area1(articleDto.getArea1())
                        .area2(articleDto.getArea2())
                        .areaName(articleDto.getAreaName())
                        .articleConfirmYmd(articleDto.getArticleConfirmYmd())
                        .articleFeatureDesc(articleDto.getArticleFeatureDesc())
                        .articleName(articleDto.getArticleName())
                        .articleRealEstateTypeCode(articleDto.getArticleRealEstateTypeCode())
                        .articleRealEstateTypeName(articleDto.getArticleRealEstateTypeName())
                        .articleStatus(articleDto.getArticleStatus())
                        .buildingName(articleDto.getBuildingName())
                        .cpMobileArticleLinkUseAtArticleTitleYn(articleDto.getCpMobileArticleLinkUseAtArticleTitleYn())
                        .cpMobileArticleLinkUseAtCpNameYn(articleDto.getCpMobileArticleLinkUseAtCpNameYn())
                        .cpMobileArticleUrl(articleDto.getCpMobileArticleUrl())
                        .cpName(articleDto.getCpName())
                        .cpPcArticleBridgeUrl(articleDto.getCpPcArticleBridgeUrl())
                        .cpPcArticleLinkUseAtArticleTitleYn(articleDto.getCpPcArticleLinkUseAtArticleTitleYn())
                        .cpPcArticleLinkUseAtCpNameYn(articleDto.getCpPcArticleLinkUseAtCpNameYn())
                        .cpPcArticleUrl(articleDto.getCpPcArticleUrl())
                        .cpid(articleDto.getCpid())
                        .dealOrWarrantPrc(articleDto.getDealOrWarrantPrc())
                        .detailAddress(articleDto.getDetailAddress())
                        .detailAddressYn(articleDto.getDetailAddressYn())
                        .direction(articleDto.getDirection())
                        .floorInfo(articleDto.getFloorInfo())
                        .isComplex(articleDto.getIsComplex())
                        .isDirectTrade(articleDto.getIsDirectTrade())
                        .isInterest(articleDto.getIsInterest())
                        .isLocationShow(articleDto.getIsLocationShow())
                        .isPriceModification(articleDto.getIsPriceModification())
                        .latitude(articleDto.getLatitude())
                        .longitude(articleDto.getLongitude())
                        .priceChangeState(articleDto.getPriceChangeState())
                        .realEstateTypeCode(articleDto.getRealEstateTypeCode())
                        .realEstateTypeName(articleDto.getRealEstateTypeName())
                        .realtorId(articleDto.getRealtorId())
                        .realtorName(articleDto.getRealtorName())
                        .representativeImgThumb(articleDto.getRepresentativeImgThumb())
                        .representativeImgTypeCode(articleDto.getRepresentativeImgTypeCode())
                        .representativeImgUrl(articleDto.getRepresentativeImgUrl())
                        .sameAddrCnt(articleDto.getSameAddrCnt())
                        .sameAddrDirectCnt(articleDto.getSameAddrDirectCnt())
                        .sameAddrMaxPrc(articleDto.getSameAddrMaxPrc())
                        .sameAddrMinPrc(articleDto.getSameAddrMinPrc())
                        .siteImageCount(articleDto.getSiteImageCount())
                        .tradeCheckedByOwner(articleDto.getTradeCheckedByOwner())
                        .tradeTypeCode(articleDto.getTradeTypeCode())
                        .tradeTypeName(articleDto.getTradeTypeName())
                        .verificationTypeCode(articleDto.getVerificationTypeCode())
                        .build();
                String tags = String.join(",",articleDto.getTagList());
                article.setTagList(tags);
                ls.add(article);
            });
        } catch (Exception e) {
            log.error("parse failed responseCode : {} responseBody : {}", response.getStatusCode(), response.getBody());
        }

        return ls;
    }
}
