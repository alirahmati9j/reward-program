package com.ali.rewardprogram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {

    @JsonProperty
    private String username;

    @JsonProperty
    private Double total;
}
