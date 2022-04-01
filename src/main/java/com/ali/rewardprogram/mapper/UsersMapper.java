package com.ali.rewardprogram.mapper;

import com.ali.rewardprogram.dto.UsersDTO;
import com.ali.rewardprogram.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    /**
     * @param users
     * @return
     */
    public UsersDTO toDto(Users users) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsername(users.getUsername());
        usersDTO.setId(users.getId());
        usersDTO.setName(users.getName());
        usersDTO.setRegistrationDate(users.getRegistrationDate());
        return usersDTO;
    }
}
