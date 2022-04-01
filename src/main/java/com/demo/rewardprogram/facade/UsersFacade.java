package com.demo.rewardprogram.facade;

import com.demo.rewardprogram.common.exceptions.UserException;
import com.demo.rewardprogram.dto.UsersDTO;
import com.demo.rewardprogram.mapper.UsersMapper;
import com.demo.rewardprogram.service.IUsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersFacade {

    private static final Logger LOGGER = LogManager.getLogger(UsersFacade.class);

    @Autowired
    private IUsersService iUsersService;

    @Autowired
    private UsersMapper usersMapper;

    /**
     * @param usersDTO
     * @return
     * @throws UserException
     */
    public UsersDTO register(UsersDTO usersDTO) throws UserException {
        LOGGER.info("Executing Facade :: {}", "register()");
        return usersMapper.toDto(iUsersService.register(usersDTO));
    }

}
