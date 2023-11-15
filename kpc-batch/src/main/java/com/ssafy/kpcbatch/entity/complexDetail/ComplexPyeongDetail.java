package com.ssafy.kpcbatch.entity.complexDetail;

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
public class ComplexPyeongDetail {

    @Id
    private Long pyeongNo;

    private Double supplyAreaDouble;
    private Double supplyArea;
    private int pyeongName;
    private Double supplyPyeong;
    private int pyeongName2;
    private Double exclusiveArea;
    private Double exclusivePyeong;
    private int householdCountByPyeong;
    private String realEstateTypeCode;
    private int exclusiveRate;

    @OneToOne
    @JoinColumn(name = "articleStatistics_no")
    ArticleStatistics articleStatistics; // 매매호가

    @OneToOne
    @JoinColumn(name = "landPriceMaxByPtp_no")
    LandPriceMaxByPtp landPriceMaxByPtp;

}
