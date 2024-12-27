package com.gambling.bet_service.controller;

import com.gambling.bet_service.DTO.BetDTO;
import com.gambling.bet_service.model.BetStatus;
import com.gambling.bet_service.service.BetService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bet")
public class BetController {

  private final BetService service;

  public BetController(BetService service) {
    this.service = service;
  }

  @GetMapping("/bets")
  public List<BetDTO> listBets(){
    return service.getBets();
  }

  @GetMapping("/{id}")
  public BetDTO getBet(@PathVariable("id") Long betId) {
    return
        Optional.ofNullable(betId)
            .map(service::getBet)
            .orElseThrow();
  }

  @PatchMapping("/{id}/status")
  public void updateStatus(@PathVariable("id") Long betId,@Valid @RequestBody BetStatus newStatus) {
    service.updateStatus(betId, newStatus);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void placeBet(@Valid @RequestBody BetDTO bet) {
    service.create(bet);
  }

  @GetMapping("/{userId}/bets")
  public List<BetDTO> listBetsByUserId(@PathVariable("userId") Long userId){
    return service.getAllBetsByUserId(userId);
  }
}

