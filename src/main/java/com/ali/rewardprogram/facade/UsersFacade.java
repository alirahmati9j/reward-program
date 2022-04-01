package com.ali.rewardprogram.facade;

import com.ali.rewardprogram.common.exceptions.UserException;
import com.ali.rewardprogram.dto.UsersDTO;
import com.ali.rewardprogram.mapper.UsersMapper;
import com.ali.rewardprogram.service.IUsersService;
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
