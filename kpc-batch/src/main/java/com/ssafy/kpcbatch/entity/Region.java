package com.ssafy.kpcbatch.entity;

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
    Long cortarNo;
    Double centerLat;
    Double centerLon;
    String cortarName;
    String cortarType;
}
