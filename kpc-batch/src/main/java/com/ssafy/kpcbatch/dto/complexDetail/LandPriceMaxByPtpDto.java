package com.ssafy.kpcbatch.dto.complexDetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LandPriceMaxByPtpDto {
    String complexNo;
    String ptpNo;
    String supplyArea;
    String totalArea;
    String minPrice;
    String maxPrice;
    Integer stdYear;
    String stdYmd;

    // landPriceTax
    Integer propertyTotalTax;
    Integer propertyTax;
    Integer localEduTax;
    Integer cityAreaTax;
    Integer realEstateTotalTax;
    Integer decisionTax;
    Integer ruralSpecialTax;
}
