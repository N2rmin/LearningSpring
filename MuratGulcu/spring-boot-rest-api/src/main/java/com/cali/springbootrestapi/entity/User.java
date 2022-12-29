package com.cali.springbootrestapi.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {
    @Id
    @SequenceGenerator(name = "user_seq_gen",sequenceName = "user_gen", initialValue = 100,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ISIM",length = 100)
    private String firstName;
    @Column(name = "SOYISIM",length = 100)
    private String lastName;
}
