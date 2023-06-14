package com.loanpro.challengebe.operation.strategy;

import com.loanpro.challengebe.operation.OperationType;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SubtractionOperationStrategy implements OperationStrategy {

    @Override
    public void validateNumbers(BigDecimal... numbers) {
        if (numbers[0] == null || numbers[1] == null) {
            throw new IllegalArgumentException("Numbers cannot be null for subtraction");
        }
    }

    @Override
    public String run(@NotNull BigDecimal... numbers) {
        this.validateNumbers(numbers);

        return numbers[0].subtract(numbers[1]).toString();
    }

    @Override
    public OperationType getName() {
        return OperationType.SUBTRACTION;
    }
}
