package com.ali.rewardprogram.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Users.TABLE_NAME, indexes = {
        @Index(name = Users.TABLE_NAME + "_INDEX_0", columnList = "id", unique = true),
        @Index(name = Users.TABLE_NAME + "_INDEX_0", columnList = "username", unique = true)
})
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Users {
    public static final String TABLE_NAME = "USERS";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "existingOrGenerated")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "registration_date")
    private Date registrationDate;
}
