package com.ali.rewardprogram.facade;

import com.ali.rewardprogram.common.exceptions.RewardException;
import com.ali.rewardprogram.common.exceptions.UserException;
import com.ali.rewardprogram.dto.OrderDTO;
import com.ali.rewardprogram.dto.PointsDTO;
import com.ali.rewardprogram.service.IRewardsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RewardsFacade {

    private static final Logger LOGGER = LogManager.getLogger(RewardsFacade.class);

    @Autowired
    private IRewardsService iRewardsService;

    public PointsDTO calculateReward(OrderDTO orderDTO) throws RewardException, UserException {
        LOGGER.info("Executing Facade :: {}", "calculateReward()");
        return iRewardsService.calculateReward(orderDTO);
    }

    public PointsDTO getReward(String username, String date) throws UserException {
        LOGGER.info("Executing Facade :: {}", "calculateReward()");
        return iRewardsService.getRewards(username, date);
    }

}
