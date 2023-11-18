package com.ssafy.kpcbatch.entity.complex;

import com.ssafy.kpcbatch.entity.complexDetail.ComplexDetail;
import com.ssafy.kpcbatch.entity.realPrice.RealPrice;
import com.ssafy.kpcbatch.entity.realPrice.RealPriceOnMonth;
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
// 단지
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
    @JoinColumn(name = "complexdetail_no")
    private ComplexDetail complexDetail;

    @OneToOne
    @JoinColumn(name = "realprice_no")
    private RealPriceOnMonth realPriceOnMonth;
}
