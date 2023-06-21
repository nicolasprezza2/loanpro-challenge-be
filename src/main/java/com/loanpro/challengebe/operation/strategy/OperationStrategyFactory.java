package com.loanpro.challengebe.operation.strategy;

import com.loanpro.challengebe.operation.OperationType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class OperationStrategyFactory {

    private Map<OperationType, OperationStrategy> operationStrategies;

    public OperationStrategyFactory(Set<OperationStrategy> strategies) {
        operationStrategies = new HashMap<>();
        strategies.forEach( st -> {
            operationStrategies.put(st.getName(), st);
        });
    }

    public OperationStrategy findOperationStrategy(OperationType type) {
        return operationStrategies.get(type);
    }
}
