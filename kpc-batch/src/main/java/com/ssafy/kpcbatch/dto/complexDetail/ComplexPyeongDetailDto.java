package com.ssafy.kpcbatch.dto.complexDetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexPyeongDetailDto {
    String pyeongNo;
    Float supplyAreaDouble;
    String supplyArea;
    String pyeongName;
    String supplyPyeong;
    String pyeongName2;
    String exclusiveArea;
    String exclusivePyeong;
    String householdCountByPyeong;
    String realEstateTypeCode;
    String exclusiveRate;
    GrandPlanListDto grandPlanListDto;
    MaintenanceCostListDto maintenanceCostListDto;
    AverageMaintenanceCostDto averageMaintenanceCostDto;
    ArticleStatisticsDto articleStatisticsDto;
    String entranceType;
    LandPriceMaxByPtpDto landPriceMaxByPtpDto;
    String roomCnt;
    String bathroomCnt;
}
