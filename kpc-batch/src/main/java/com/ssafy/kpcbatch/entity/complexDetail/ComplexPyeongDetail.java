package com.ssafy.kpcbatch.entity.complexDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ComplexPyeongDetail.ComplexPyeongDetailPK.class)
public class ComplexPyeongDetail {
    @Id
    private Long complexNo;
    @Id
    private Long pyeongNo;
    private Double supplyAreaDouble;
    private Double supplyArea;
    private String pyeongName;
    private Double supplyPyeong;
    private String pyeongName2;
    private Double exclusiveArea;
    private Double exclusivePyeong;
    private int householdCountByPyeong;
    private String realEstateTypeCode;
    private int exclusiveRate;

    @OneToOne
    @JoinColumn(name = "complexNo", insertable = false, updatable = false)
    @JoinColumn(name = "pyeongNo", insertable = false, updatable = false)
    ArticleStatistics articleStatistics; // 매매호가

    @OneToOne
    @JoinColumn()
    LandPriceMaxByPtp landPriceMaxByPtp;

    class ComplexPyeongDetailPK implements Serializable {
        private Long complexNo;
        private Long pyeongNo;
    }
}