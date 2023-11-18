package com.ssafy.kpcbatch.entity.realPrice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RealPrice {

    @Id @GeneratedValue
    @Column(name = "realpriceNo")
    Long realPriceNo;

    String tradeYear;
    Integer tradeMonth;
    String tradeType;
    String tradeDate;
    Integer dealPrice;
    Integer floor;
    Float representativeArea;
    Float exclusiveArea;
    String formattedPricce;
    String formattedTradeYearMonth;

}
