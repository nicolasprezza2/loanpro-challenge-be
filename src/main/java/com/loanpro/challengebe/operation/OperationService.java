package com.loanpro.challengebe.operation;

import com.loanpro.challengebe.record.Record;

import java.math.BigDecimal;

public interface OperationService {

    Record execute(OperationType opType, BigDecimal firstNumber, BigDecimal secondNumber);
}
