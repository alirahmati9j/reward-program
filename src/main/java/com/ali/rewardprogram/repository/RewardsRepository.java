package com.ali.rewardprogram.repository;

import com.ali.rewardprogram.entity.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards, Integer> {

    /**
     * @param userId
     * @param date
     * @return
     */
    @Query("SELECT sum(r.rewards) from Rewards r WHERE r.users.id = :userId and DATE_FORMAT(r.purchaseDate, '%m-%Y') = :date")
    public Integer calculateRewards(@Param("userId") int userId, @Param("date") String date);

}
