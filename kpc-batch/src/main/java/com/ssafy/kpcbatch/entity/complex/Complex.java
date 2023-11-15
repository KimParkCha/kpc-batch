package com.ssafy.kpcbatch.entity.complex;

import com.ssafy.kpcbatch.entity.complexDetail.ComplexDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="complex")
public class Complex {
    @Id
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

    @OneToOne
    @JoinColumn(name = "complexNo")
    private ComplexDetail complexDetail;
}
