package com.ssafy.kpcbatch.entity.complexDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleStatistics {

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

}
