package com.loanpro.challengebe.operation;

import com.loanpro.challengebe.operation.strategy.OperationStrategy;
import com.loanpro.challengebe.operation.strategy.OperationStrategyFactory;
import com.loanpro.challengebe.record.Record;
import com.loanpro.challengebe.record.RecordService;
import com.loanpro.challengebe.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class OperationServiceImpl implements OperationService {
    private final RecordService recordService;
    private final OperationRepository repository;
    private final OperationStrategyFactory strategyFactory;

    @Autowired
    public OperationServiceImpl(RecordService recordService,
                                OperationRepository repository,
                                OperationStrategyFactory strategyFactory) {
        this.recordService = recordService;
        this.repository = repository;
        this.strategyFactory = strategyFactory;
    }

    @Override
    public Record execute(OperationType opType, BigDecimal firstNumber, BigDecimal secondNumber) {
        // Get current user
        User currentUser = new User("name", "password"); // REPLACE FOR CURRENT USER
        // Find operation
        Operation operation = this.repository.findByType(opType);
        // Calculate new balance
        BigDecimal newBalance  = this.calculateNewBalance(currentUser, operation);
        // Resolve operation strategy
        OperationStrategy strategy = this.strategyFactory.findOperationStrategy(opType);
        // Run strategy
        String operationResponse = strategy.run(firstNumber, secondNumber);
        // Build record and persist it
        return this.recordService.add(operation, currentUser, operation.getCost(), newBalance, operationResponse);
    }

    private BigDecimal calculateNewBalance(User user, Operation operation) {
        // Get current user's balance
        BigDecimal currentBalance = this.recordService.getBalanceForUser(user);
        return currentBalance.subtract(operation.getCost());
        /**BigDecimal newBalance = currentBalance.subtract(operation.getCost());
        if (newBalance.compareTo(BigDecimal.ZERO) === -1) {
            throw new RuntimeException("Does not have enough balance to perform operation");
        }
        return newBalance; **/
    }
}
