package com.gambling.bet_service.service;

import com.gambling.bet_service.DTO.BetDTO;
import com.gambling.bet_service.exceptions.BetNotFoundException;
import com.gambling.bet_service.mapper.BetMapper;
import com.gambling.bet_service.model.Bet;
import com.gambling.bet_service.model.BetStatus;
import com.gambling.bet_service.repository.BetRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BetServiceImpl implements BetService {

  private final BetRepository repository;

  public BetServiceImpl(BetRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<BetDTO> getBets() {
    return repository.findAll()
        .stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  @Override
  public BetDTO getBet(Long betId) {
    return Optional.ofNullable(betId)
        .flatMap(repository::findById)
        .map(this::convertToDTO)
        .orElseThrow(() -> new BetNotFoundException("Bet with id " + betId + " not found"));
  }

  @Override
  public void create(BetDTO betDTO) {
      Bet bet = BetMapper.INSTANCE.toEntity(betDTO);
      bet.setStatus(BetStatus.PLACED);
      repository.save(bet);
  }

  @Override
  public void updateStatus(Long betId, BetStatus status) {
    Bet bet = repository.findById(betId)
        .orElseThrow(() -> new BetNotFoundException("Bet with id " + betId + " not found"));
    bet.setStatus(status);
    repository.save(bet);
  }

  @Override
  public List<BetDTO> getAllBetsByUserId(Long userId) {
    return repository.findByUserId(userId)
        .stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  private BetDTO convertToDTO(Bet bet) {
    return BetMapper.INSTANCE.toDTO(bet);
  }

}
