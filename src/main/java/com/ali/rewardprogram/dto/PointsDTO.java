package com.ali.rewardprogram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PointsDTO {

    @JsonProperty("earned_points")
    private int earnedPoints;
}
