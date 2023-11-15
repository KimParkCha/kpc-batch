package com.ssafy.kpcbatch.entity.complexDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="complexPyeongDetail")
public class ComplexPyeongDetail {
    @Id
    Long pyeongNo;
}
