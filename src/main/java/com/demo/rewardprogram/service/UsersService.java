package com.demo.rewardprogram.service;

import com.demo.rewardprogram.common.exceptions.UserException;
import com.demo.rewardprogram.dto.UsersDTO;
import com.demo.rewardprogram.entity.Users;
import com.demo.rewardprogram.repository.UsersRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsersService implements IUsersService {

    private final static Logger LOGGER = LogManager.getLogger(UsersService.class);

    @Autowired
    private UsersRepository usersRepository;

    /**
     * @param users
     * @return
     */
    @Override
    public Users register(UsersDTO users) throws UserException {

        LOGGER.info("Executing Service :: {}", "register()");
        LOGGER.info("Service Parameters :: {}", users);

        if (StringUtils.isBlank(users.getUsername())) {
            LOGGER.error("Invalid Username");
            throw new UserException("Invalid Username");
        }

        if (StringUtils.isBlank(users.getName())) {
            LOGGER.error("Invalid Name");
            throw new UserException("Invalid Name");
        }

        Users dbUser = usersRepository.findByUsername(users.getUsername()).orElse(new Users());

        LOGGER.info("User Retrieved from DB : {}", dbUser);

        if (!dbUser.equals(new Users())) {
            LOGGER.error("Username unavailable. Try with another username.");
            throw new UserException("Username unavailable. Try with another username.");
        }

        dbUser = new Users();
        dbUser.setUsername(users.getUsername());
        dbUser.setName(users.getName());
        dbUser.setRegistrationDate(new Date());

        LOGGER.info("User Saved to DB : {}", dbUser);

        return usersRepository.save(dbUser);
    }

    /**
     * @param username
     * @return
     * @throws UserException
     */
    @Override
    public Users get(String username) throws UserException {
        LOGGER.info("Executing Service :: {}", "get()");
        LOGGER.info("Service Parameters :: {}", username);
        return usersRepository.findByUsername(username).orElseThrow(() -> new UserException("User does not exist."));
    }
}
