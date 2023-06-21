package com.loanpro.challengebe.operation.strategy;

import com.loanpro.challengebe.operation.OperationType;

import java.math.BigDecimal;

public interface OperationStrategy {

    void validateNumbers(BigDecimal ...numbers);

    String run(BigDecimal ...numbers);

    OperationType getName();


}
