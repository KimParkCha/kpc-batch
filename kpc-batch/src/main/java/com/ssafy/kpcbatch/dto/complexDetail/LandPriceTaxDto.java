package com.ssafy.kpcbatch.dto.complexDetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LandPriceTaxDto {
    Integer propertyTotalTax;
    Integer propertyTax;
    Integer localEduTax;
    Integer cityAreaTax;
    Integer realEstateTotalTax;
    Integer decisionTax;
    Integer ruralSpecialTax;
}
