package com.loanpro.challengebe.operation;

import com.loanpro.challengebe.operation.initiator.OperationInitiator;
import com.loanpro.challengebe.operation.strategy.OperationStrategy;
import com.loanpro.challengebe.operation.strategy.OperationStrategyFactory;
import com.loanpro.challengebe.record.Record;
import com.loanpro.challengebe.record.RecordService;
import com.loanpro.challengebe.user.User;
import com.loanpro.challengebe.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final RecordService recordService;
    private final OperationStrategyFactory strategyFactory;

    @Override
    public Record execute(OperationType opType, BigDecimal firstNumber, BigDecimal secondNumber) {
        // Get current user
        User user = SecurityUtils.getCurrentUser();
        // Find operation
        Operation operation = OperationInitiator.getByType(opType);
        // Calculate new balance
        BigDecimal oldBalance = this.recordService.getBalanceForUser(user);
        BigDecimal newBalance = oldBalance.subtract(operation.getCost());
        Record record;
        if (hasEnoughBalance(newBalance)) {
            record = this.runStrategy(operation, firstNumber, secondNumber, user, newBalance);
        } else {
            record = this.buildNotEnoughBalanceRecord(operation, user, oldBalance);
        }
        return this.recordService.save(record);
    }

    private Record buildNotEnoughBalanceRecord(Operation operation, User user, BigDecimal balance) {
        return new Record(operation, user, BigDecimal.ZERO, balance, "Not enough balance");
    }

    private Record runStrategy(Operation operation,
                               BigDecimal firstNumber,
                               BigDecimal secondNumber,
                               User user,
                               BigDecimal newBalance) {
        // Resolve operation strategy
        OperationStrategy strategy = this.strategyFactory.findOperationStrategy(operation.getType());
        // Run strategy
        String operationResponse = strategy.run(firstNumber, secondNumber);
        return new Record(operation, user, operation.getCost(), newBalance, operationResponse);
    }

    private boolean hasEnoughBalance(BigDecimal balance) {
        return balance.compareTo(BigDecimal.ZERO) >= 0;
    }
}
