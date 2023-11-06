package com.ssafy.kpcbatch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Region {
    Long cortarNo;
    Double centerLat;
    Double centerLon;
    String cortarName;
    String cortarType;

}
