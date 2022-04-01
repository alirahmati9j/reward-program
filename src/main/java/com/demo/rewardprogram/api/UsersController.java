package com.demo.rewardprogram.api;

import com.demo.rewardprogram.common.exceptions.UserException;
import com.demo.rewardprogram.dto.UsersDTO;
import com.demo.rewardprogram.facade.UsersFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UsersController {

    private static final Logger LOGGER = LogManager.getLogger(UsersController.class);

    @Autowired
    private UsersFacade usersFacade;

    /**
     * @param usersDTO
     * @return
     * @throws UserException
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UsersDTO> register(@RequestBody UsersDTO usersDTO) throws UserException {
        LOGGER.info("Executing Controller :: {}", "register()");
        return new ResponseEntity<>(usersFacade.register(usersDTO), HttpStatus.OK);
    }

}
