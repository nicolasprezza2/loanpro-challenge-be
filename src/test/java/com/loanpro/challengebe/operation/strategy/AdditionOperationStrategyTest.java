package com.loanpro.challengebe.operation.strategy;

import com.loanpro.challengebe.operation.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdditionOperationStrategyTest {

    private AdditionOperationStrategy target;

    @BeforeEach
    public void init() {
        target = new AdditionOperationStrategy();
    }

    @Test
    void givenCorrectNumbers_shouldNotThrowException() {
        target.validateNumbers(new BigDecimal(1), new BigDecimal(2));
    }
    @Test
    void givenInvalidNumbers_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            target.validateNumbers(new BigDecimal(1), null);
        });

        String expectedMessage = "Numbers cannot be null for addition";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldRunStrategy_ok() {

    }

    @Test
    void returnsCorrectName() {
        assertEquals(OperationType.ADDITION, target.getName());
    }
}
