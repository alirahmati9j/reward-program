package com.demo.rewardprogram.api;

import com.demo.rewardprogram.common.exceptions.RewardException;
import com.demo.rewardprogram.common.exceptions.UserException;
import com.demo.rewardprogram.dto.OrderDTO;
import com.demo.rewardprogram.dto.PointsDTO;
import com.demo.rewardprogram.facade.RewardsFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reward")
public class RewardsController {

    private static final Logger LOGGER = LogManager.getLogger(RewardsController.class);

    @Autowired
    private RewardsFacade rewardsFacade;

    /**
     * @param orderDTO
     * @return
     * @throws RewardException
     * @throws UserException
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<PointsDTO> calculateReward(@RequestBody OrderDTO orderDTO) throws RewardException, UserException {
        LOGGER.info("Executing Controller :: {}", "calculateReward()");
        return new ResponseEntity<>(rewardsFacade.calculateReward(orderDTO), HttpStatus.OK);
    }

    /**
     * @param username
     * @return
     * @throws UserException
     */
    @GetMapping(consumes = "application/json")
    public ResponseEntity<PointsDTO> getReward(@RequestParam String username, @RequestParam String date) throws UserException {
        LOGGER.info("Executing Controller :: {}", "getReward()");
        return new ResponseEntity<>(rewardsFacade.getReward(username, date), HttpStatus.OK);
    }
}
