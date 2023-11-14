package com.ssafy.kpcbatch.dto.complexDetail;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoDto {
    Integer imageKey;
    String imageSrc;
    Long imageId;
    String newOldGbn;
    String smallCategoryName;
    String explaination;
    String registYmdt;
    int imageOrder;
}
