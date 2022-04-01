package com.demo.rewardprogram.service;

import com.demo.rewardprogram.common.exceptions.RewardException;
import com.demo.rewardprogram.common.exceptions.UserException;
import com.demo.rewardprogram.dto.OrderDTO;
import com.demo.rewardprogram.dto.PointsDTO;
import org.springframework.data.repository.query.Param;

public interface IRewardsService {

    /**
     * @param orderDTO
     * @return
     * @throws RewardException
     * @throws UserException
     */
    public PointsDTO calculateReward(OrderDTO orderDTO) throws RewardException, UserException;

    /**
     * @param username
     * @param date
     * @return
     * @throws UserException
     */
    public PointsDTO getRewards(String username, String date) throws UserException;

}
