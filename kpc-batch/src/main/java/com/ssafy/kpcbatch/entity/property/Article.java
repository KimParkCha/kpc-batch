package com.ssafy.kpcbatch.entity.property;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Article {
    @Id
    String articleNo;
    Long complexNo;
    Integer area1;
    Integer area2;
    String areaName;
    String articleConfirmYmd;
    String articleFeatureDesc;
    String articleName;
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
    String tagList;
    Boolean tradeCheckedByOwner;
    String tradeTypeCode;
    String tradeTypeName;
    String verificationTypeCode;
}
