package com.demo.rewardprogram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class UsersDTO {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String username;

    @JsonProperty
    private String name;

    @JsonProperty
    private Date registrationDate;
}
