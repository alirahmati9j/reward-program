package com.ali.rewardprogram.service;

import com.ali.rewardprogram.common.exceptions.UserException;
import com.ali.rewardprogram.dto.UsersDTO;
import com.ali.rewardprogram.entity.Users;

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
