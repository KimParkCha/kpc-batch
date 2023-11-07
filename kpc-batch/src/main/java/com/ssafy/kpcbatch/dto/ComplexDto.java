package com.ssafy.kpcbatch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexDto {
    Long complexNo;
    String complexName;
    Long cortarNo;
    String realEstateTypeCode;
    String realEstateTypeName;
    String detailAddress;
    Double latitude;
    Double longitude;
    int totalHouseholdCount;
    int totalBuildingCount;
    int highFloor;
    int lowFloor;
    String useApproveYmd;
    int dealCount;
    int leaseCount;
    int rentCount;
    int shirtTermRentCount;
    String cortarAddress;
}
