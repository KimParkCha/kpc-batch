package com.ssafy.kpcbatch.dto.realPrice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealPriceDto {

    String tradeType;
    String tradeYear;
    Integer tradeMonth;
    String tradeDate;
    Integer dealPrice;
    Integer floor;
    Float representativeArea;
    Float exclusiveArea;
    String formattedPricce;
    String formattedTradeYearMonth;
}
