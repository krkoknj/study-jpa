package com.mystudy.myjpamvn.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Members  {
    @Id
    @Column(name = "MEMBERS_ID")
    private String id;
    private String username;
    
    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Members(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
