package com.mystudy.myjpamvn.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

  @Id
  @Column(name = "MEMBER_ID")
  @GeneratedValue
  private Long Id;

  private String name;

  private String city;

  private String street;

  private String zipcode;
}
