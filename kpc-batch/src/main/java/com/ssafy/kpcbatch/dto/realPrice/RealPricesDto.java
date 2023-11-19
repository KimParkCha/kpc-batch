package com.ssafy.kpcbatch.dto.realPrice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealPricesDto {
    Integer areaNo;
    List<RealPriceOnMonthDto> realPriceOnMonthList;
    Integer addedRowCount;
    Integer totalRowCount;
    String realPriceBasisYearMonth;
}
