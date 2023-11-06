package com.ssafy.kpcbatch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionDto {
    Long cortarNo;
    Double centerLat;
    Double centerLon;
    String cortarName;
    String cortarType;

}
