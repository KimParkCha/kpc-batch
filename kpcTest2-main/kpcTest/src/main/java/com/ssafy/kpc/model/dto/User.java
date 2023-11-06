package com.ssafy.kpc.model.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String userName;
    private String userPwd;
    private String emailId;
    private String emailDomain;
    private String joinDate;

}
