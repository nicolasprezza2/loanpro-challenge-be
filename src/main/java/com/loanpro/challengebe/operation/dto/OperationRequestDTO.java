package com.loanpro.challengebe.operation.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class OperationRequestDTO {
    @NotNull
    private String type;
    @NotNull
    private BigDecimal firstNumber;
    private BigDecimal secondNumber;

}
