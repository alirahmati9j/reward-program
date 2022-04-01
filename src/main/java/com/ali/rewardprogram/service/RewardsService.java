package com.ali.rewardprogram.service;

import com.ali.rewardprogram.common.exceptions.RewardException;
import com.ali.rewardprogram.common.exceptions.UserException;
import com.ali.rewardprogram.dto.OrderDTO;
import com.ali.rewardprogram.dto.PointsDTO;
import com.ali.rewardprogram.entity.Rewards;
import com.ali.rewardprogram.entity.Users;
import com.ali.rewardprogram.repository.RewardsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RewardsService implements IRewardsService {

    private static final Logger LOGGER = LogManager.getLogger(RewardsService.class);

    @Autowired
    private IUsersService iUsersService;

    @Autowired
    private RewardsRepository rewardsRepository;

    /**
     * @param orderDTO
     * @return
     * @throws RewardException
     * @throws UserException
     */
    @Override
    public PointsDTO calculateReward(OrderDTO orderDTO) throws RewardException, UserException {

        LOGGER.info("Executing Service :: {}", "calculateReward()");
        LOGGER.info("Service Parameters :: {}", orderDTO);

        Users users = iUsersService.get(orderDTO.getUsername());

        LOGGER.info("User Retrieved {} :: {}", orderDTO.getUsername(), users);

        int rewardEarned = 0;
        if (orderDTO.getTotal() <= 0.0) {
            LOGGER.error("Order total should be grater than 0.0");
            throw new RewardException("Order total should be grater than 0.0");
        }

        LOGGER.error("Reward Earned :: {}", rewardEarned);

        if (orderDTO.getTotal() - 50.0 > 0.0) {
            rewardEarned = Double.valueOf(orderDTO.getTotal() - 50.0).intValue();
        }

        LOGGER.error("Reward Earned after $50 :: {}", rewardEarned);

        if (orderDTO.getTotal() - 100.0 > 0.0) {
            rewardEarned = Double.valueOf((orderDTO.getTotal() - 100.0) * 2).intValue() + 50;
        }

        LOGGER.error("Reward Earned after $100 :: {}", rewardEarned);

        Rewards rewards = new Rewards();
        rewards.setRewards(rewardEarned);
        rewards.setPurchaseDate(new Date());
        rewards.setTotal(orderDTO.getTotal());
        rewards.setUsers(users);
        rewardsRepository.save(rewards);

        LOGGER.info("Final Points Earned :: {}", rewardEarned);

        return new PointsDTO(rewardEarned);
    }

    /**
     * @param username
     * @param date
     * @return
     * @throws UserException
     */
    @Override
    public PointsDTO getRewards(String username, String date) throws UserException {
        LOGGER.info("Executing Service :: {}", "getRewards()");
        LOGGER.info("Service Parameters :: Username={}, date={}", username, date);

        Users dbUser = iUsersService.get(username);

        LOGGER.info("User Retrieved {} :: {}", username, dbUser);

        Integer earnedRewards = rewardsRepository.calculateRewards(dbUser.getId(), date);

        LOGGER.info("Points Retrieved :: {}", earnedRewards);

        return new PointsDTO(null == earnedRewards ? 0 : earnedRewards);
    }
}
