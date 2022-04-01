package com.demo.rewardprogram.service;

import com.demo.rewardprogram.common.exceptions.UserException;
import com.demo.rewardprogram.dto.UsersDTO;
import com.demo.rewardprogram.entity.Users;

public interface IUsersService {

    /**
     * @param users
     * @return
     * @throws UserException
     */
    public Users register(UsersDTO users) throws UserException;

    /**
     * @param username
     * @return
     * @throws UserException
     */
    public Users get(String username) throws UserException;

}
