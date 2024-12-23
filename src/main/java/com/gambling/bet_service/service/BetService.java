package com.gambling.bet_service.service;

import com.gambling.bet_service.DTO.BetDTO;
import com.gambling.bet_service.model.BetStatus;
import java.util.List;

public interface BetService {
  public List<BetDTO> getBets();
  public BetDTO getBet(Long betId);
  public void create(BetDTO betDTO);
  public void updateStatus(Long betId, BetStatus status);
  public List<BetDTO> getAllBetsByUserId(Long userId);

}
