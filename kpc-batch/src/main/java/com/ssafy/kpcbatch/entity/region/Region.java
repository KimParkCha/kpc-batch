package com.ssafy.kpcbatch.entity.region;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="region")
public class Region {
    @Id
    Long cortarNo; // 법정동코드
    Double centerLat; // 위도
    Double centerLon; // 경도
    String cortarName; // 법정동이름
    String cortarType; // 법정동타입
}
