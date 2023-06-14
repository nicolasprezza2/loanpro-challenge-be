package com.loanpro.challengebe.operation.strategy;

import com.loanpro.challengebe.operation.OperationType;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DivisionOperationStrategy implements OperationStrategy {

    @Override
    public void validateNumbers(BigDecimal... numbers) {
        if (numbers[0] == null) {
            throw new IllegalArgumentException("Dividend cannot be null");
        }

        if (numbers[1] == null || numbers[1].compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Divisor cannot be null or 0");
        }
    }

    @Override
    public String run(@NotNull  BigDecimal ...numbers) {
        this.validateNumbers(numbers);

        return numbers[0].divide(numbers[1]).toString();
    }

    @Override
    public OperationType getName() {
        return OperationType.DIVISION;
    }
}
