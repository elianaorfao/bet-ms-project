package com.gambling.bet_service.mapper;

import com.gambling.bet_service.DTO.BetDTO;
import com.gambling.bet_service.model.Bet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BetMapper  {

  BetMapper INSTANCE = Mappers.getMapper(BetMapper.class);

  BetDTO toDTO(Bet user);

  @Mapping(target = "betId", ignore = true)
  @Mapping(target = "entryDate", ignore = true)
  @Mapping(target = "lastUpdateDate", ignore = true)
  Bet toEntity(BetDTO userDTO);
}
