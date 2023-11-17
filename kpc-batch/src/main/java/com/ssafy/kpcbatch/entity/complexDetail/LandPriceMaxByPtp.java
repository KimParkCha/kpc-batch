package com.ssafy.kpcbatch.entity.complexDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LandPriceMaxByPtp.LandPriceMaxByPtpPK.class)
public class LandPriceMaxByPtp { // 재산세

    @Id
    String ptpNo;
    @Id
    Long complexNo;
    private String supplyArea;
    private String totalArea;
    private String minPrice;
    private String maxPrice;
    private Integer stdYear;
    private String stdYmd;

    // landPriceTax
    Integer propertyTotalTax; // 재산세 합계
    Integer propertyTax;
    Integer localEduTax; // 지방 교육세
    Integer cityAreaTax; // 재산세_도시지역분
    Integer realEstateTotalTax; // 종합부동산세
    Integer decisionTax; // 결정세액
    Integer ruralSpecialTax; // 농어촌특별세

    @OneToOne(mappedBy = "landPriceMaxByPtp")
    ComplexPyeongDetail complexPyeongDetail;

    class LandPriceMaxByPtpPK implements Serializable {
        private Long complexNo;
    }
}