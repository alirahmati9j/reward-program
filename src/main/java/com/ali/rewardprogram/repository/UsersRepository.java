package com.ali.rewardprogram.repository;

import com.ali.rewardprogram.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    /**
     * @param username
     * @return
     */
    public Optional<Users> findByUsername(String username);

}
