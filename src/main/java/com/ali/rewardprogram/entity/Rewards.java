package com.ali.rewardprogram.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Rewards.TABLE_NAME, indexes = {
        @Index(name = Rewards.TABLE_NAME + "_INDEX_0", columnList = "id", unique = true)
})
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Rewards {
    public static final String TABLE_NAME = "REWARDS";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "existingOrGenerated")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column
    private Double total;

    @Column
    private Integer rewards;
}
