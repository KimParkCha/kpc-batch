package com.ssafy.kpcbatch.dto.complexDetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexPyeongDetailDto {
    String complexNo;
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
    List<GrandPlanDto> grandPlanList;
    List<MaintenanceCostDto> maintenanceCostList;
    AverageMaintenanceCostDto averageMaintenanceCost;
    ArticleStatisticsDto articleStatistics;
    String entranceType;
    LandPriceMaxByPtpDto landPriceMaxByPtp;
    String roomCnt;
    String bathroomCnt;
    String articleStatisticsNo;
    String landPriceMaxByPtpNo;
}
