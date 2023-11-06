package com.ssafy.kpc.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOARD")
@Getter @Setter
public class Board {

    @Id
    @GeneratedValue
    private int seq;
    private String title;
    private String writer;
    private String content;

    @Temporal(TemporalType.DATE)
    private Date regDate = new Date();
    private int cnt;

}
