package com.ssafy.kpcbatch.entity.complexDetail;

import com.ssafy.kpcbatch.entity.complex.Complex;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ComplexPyeongDetail.ComplexPyeongDetailPK.class)
@Getter @Setter
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
    @JoinColumn()
    ArticleStatistics articleStatistics; // 매매호가

    @OneToOne
    @JoinColumn()
    LandPriceMaxByPtp landPriceMaxByPtp;

    class ComplexPyeongDetailPK implements Serializable {
        private Long complexNo;
        private Long pyeongNo;
    }
}
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "complexDetail_no")
//    private ComplexDetail complexDetail;


