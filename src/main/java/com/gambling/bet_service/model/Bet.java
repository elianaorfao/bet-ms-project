package com.gambling.bet_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "T_BET")
@Entity
public class Bet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long betId;

  @Column(name = "USER_ID", length = 30)
  @NotNull(message = "User ID cannot be null")
  private Long userId;

  @Column(name = "STATUS", length = 30)
  @NotNull(message = "Status cannot be null")
  @Enumerated(EnumType.STRING)
  private BetStatus status;

  @Column(name = "BET_AMOUNT", length = 30)
  @NotNull(message = "Bet amount cannot be null")
  private BigDecimal betAmount;

  @Column(name = "BET_TYPE", length = 30)
  @NotNull(message = "Bet type cannot be null")
  @Enumerated(EnumType.STRING)
  private BetType betType;

  @JsonIgnore
  @CreationTimestamp
  @Column(name = "ENTRY_DATE")
  private LocalDateTime entryDate;

  @JsonIgnore
  @UpdateTimestamp
  @Column(name = "LAST_UPDATE_DATE")
  private LocalDateTime lastUpdateDate;


}
