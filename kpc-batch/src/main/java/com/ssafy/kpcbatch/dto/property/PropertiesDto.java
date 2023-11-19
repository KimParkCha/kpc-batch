package com.ssafy.kpcbatch.dto.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertiesDto {
    List<ArticleDto> articleList;
    Boolean isMoreData;
    Integer mapExposedCount;
    Boolean nonMapExposedIncluded;
}
