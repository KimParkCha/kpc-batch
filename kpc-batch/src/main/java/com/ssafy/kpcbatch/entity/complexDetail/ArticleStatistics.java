package com.ssafy.kpcbatch.entity.complexDetail;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ArticleStatistics.ArticleStatisticsPK.class)
@Getter @Setter
public class ArticleStatistics {
    @Id
    Long complexNo;
    @Id
    Integer pyeongNo;
    String dealCount;
    String leaseCount;
    String rentCount;
    String shortTermRentCount;
    String dealPriceMin;
    String dealPriceMax;
    String leasePriceMin;
    String leasePriceMax;
    String dealPricePerSpaceMin;
    String dealPricePerSpaceMax;
    String leasePricePerSpaceMin;
    String leasePricePerSpaceMax;
    String leasePriceRateMin;
    String leasePriceRateMax;
    String dealPriceString;
    String dealPricePerSpaceString;
    String leasePriceString;
    String leasePricePerSpaceString;
    String leasePriceRateString;
    String rentPriceString;
    String rentDepositPriceMin;
    String rentPriceMin;
    String rentDepositPriceMax;
    String rentPriceMax;
    @OneToOne(mappedBy = "articleStatistics")
    ComplexPyeongDetail complexPyeongDetail;

    class ArticleStatisticsPK implements Serializable {
        private Long complexNo;
        private Integer pyeongNo;
    }
}
