package com.ssafy.kpcbatch.dto.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
    String complexNo;
    Integer area1;
    Integer area2;
    String areaName;
    String articleConfirmYmd;
    String articleFeatureDesc;
    String articleName;
    String articleNo;
    String articleRealEstateTypeCode;
    String articleRealEstateTypeName;
    String articleStatus;
    String buildingName;
    Boolean cpMobileArticleLinkUseAtArticleTitleYn;
    Boolean cpMobileArticleLinkUseAtCpNameYn;
    String cpMobileArticleUrl;
    String cpName;
    String cpPcArticleBridgeUrl;
    String cpPcArticleLinkUseAtArticleTitleYn;
    String cpPcArticleLinkUseAtCpNameYn;
    String cpPcArticleUrl;
    String cpid;
    String dealOrWarrantPrc;
    String detailAddress;
    String detailAddressYn;
    String direction;
    String floorInfo;
    Boolean isComplex;
    Boolean isDirectTrade;
    Boolean isInterest;
    Boolean isLocationShow;
    Boolean isPriceModification;
    Double latitude;
    Double longitude;
    String priceChangeState;
    String realEstateTypeCode;
    String realEstateTypeName;
    String realtorId;
    String realtorName;
    String representativeImgThumb;
    String representativeImgTypeCode;
    String representativeImgUrl;
    Integer sameAddrCnt;
    Integer sameAddrDirectCnt;
    String sameAddrMaxPrc;
    String sameAddrMinPrc;
    String siteImageCount;
    List<String> tagList;
    Boolean tradeCheckedByOwner;
    String tradeTypeCode;
    String tradeTypeName;
    String verificationTypeCode;
}
