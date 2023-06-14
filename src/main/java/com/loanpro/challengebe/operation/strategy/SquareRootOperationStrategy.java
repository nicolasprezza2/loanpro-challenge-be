package com.loanpro.challengebe.operation.strategy;

import com.loanpro.challengebe.operation.OperationType;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SquareRootOperationStrategy implements OperationStrategy {

    @Override
    public void validateNumbers(BigDecimal... numbers) {
        if (numbers[0] == null) {
            throw new IllegalArgumentException("Number cannot be null for square root");
        }
    }

    @Override
    public String run(@NotNull BigDecimal ...numbers) {
        this.validateNumbers(numbers);

        return String.valueOf(Math.sqrt(numbers[0].doubleValue()));
    }

    @Override
    public OperationType getName() {
        return OperationType.SQUARE_ROOT;
    }
}
