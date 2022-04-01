package com.demo.rewardprogram.mapper;

import com.demo.rewardprogram.dto.UsersDTO;
import com.demo.rewardprogram.entity.Users;
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
