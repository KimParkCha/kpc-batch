package com.ssafy.kpcbatch.dto.complexDetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexDetailDto {
    Long complexNo;
    String complexName;
    Long cortarNo;
    String realEstateTypeCode;
    String realEstateTypeName;
    String detailAddress;
    String roadAddress;
    Double latitude;
    Double longitude;
    Integer totalHouseholdCount;
    Integer totalLeaseHouseholdCount;
    Integer permanentLeaseHouseholdCount;
    Integer nationLeaseHouseholdCount;
    Integer civilLeaseHouseholdCount;
    Integer publicLeaseHouseholdCount;
    Integer longTermLeaseHouseholdCount;
    Integer etcLeaseHouseholdCount;
    Integer highFloor;
    Integer lowFloor;
    String useApprovueYmd;
    Integer totalDongCount;
    Float maxSupplyArea;
    Float minSupplyArea;
    Integer dealCount;
    Integer rentCount;
    Integer leaseCount;
    Integer shortTermRentCount;
    Boolean isBookmarked;
    String batlRatio;
    String btlRatio;
    Integer parkingPossibleCount;
    float parkingCountByHousehold;
    String constructionCompanyName;
    String heatMethodTypeCode;
    String heatFuelTypeCode;
    String pyoengNames;
    String managementOfficeTelNo;
    String buildingRegister;
    String address;
    String roadAddressPrefix;
    String roadZipCode;
}
