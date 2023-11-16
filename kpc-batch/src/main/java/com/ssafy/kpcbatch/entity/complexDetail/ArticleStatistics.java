package com.ssafy.kpcbatch.entity.complexDetail;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ArticleStatistics {

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

    @OneToOne(mappedBy = "articleStatistics")
    ComplexPyeongDetail complexPyeongDetail;
}
