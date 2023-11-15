package com.ssafy.kpcbatch.entity.complexDetail;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="complexDetail")
public class ComplexDetail {
    @Id
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
    String useApproveYmd;
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
