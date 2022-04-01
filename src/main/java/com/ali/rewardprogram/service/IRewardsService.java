package com.ali.rewardprogram.service;

import com.ali.rewardprogram.common.exceptions.RewardException;
import com.ali.rewardprogram.common.exceptions.UserException;
import com.ali.rewardprogram.dto.OrderDTO;
import com.ali.rewardprogram.dto.PointsDTO;
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
