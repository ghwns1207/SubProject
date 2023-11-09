package com.example.SubProject.Entity;

//import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(length = 15)
    private String phone;
    @Column(length = 50)
    private String email;

    @Column(nullable = false, columnDefinition = "integer default 1")
    private Integer useyn;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "join_date")
    private Date joinDate;





}
