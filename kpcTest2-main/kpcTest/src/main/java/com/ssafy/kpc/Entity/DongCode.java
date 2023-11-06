package com.ssafy.kpc.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dongcode")
@Getter @Setter
public class DongCode {

    @Id @GeneratedValue
    @Column(name = "dongCode")
    private String dongCode;

    private String sidoName;
    private String gugunName;
    private String dongName;

}
