package com.gambling.bet_service.DTO;


import com.gambling.bet_service.model.BetStatus;
import com.gambling.bet_service.model.BetType;
import java.math.BigDecimal;

public record BetDTO(
    Long userId,
    BetStatus status,
    BigDecimal betAmount,
    BetType betType) {

}
