package com.gambling.bet_service.repository;

import com.gambling.bet_service.model.Bet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
  List<Bet> findByUserId(Long UserId);
}
