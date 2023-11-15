package com.ssafy.kpcbatch.entity.complexDetail;

import com.ssafy.kpcbatch.dto.complexDetail.ArticleStatisticsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "complexPyeongDetail")
public class ComplexPyeongDetail {

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

    private ArticleStatistics articleStatistics; // 매매호가

}
