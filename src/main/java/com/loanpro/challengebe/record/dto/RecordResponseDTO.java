package com.loanpro.challengebe.record.dto;

import com.loanpro.challengebe.operation.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class RecordResponseDTO {

    private Long id;
    private OperationType type;
    private BigDecimal cost;
    private BigDecimal userBalance;
    private String result;
    private LocalDateTime date;
}
