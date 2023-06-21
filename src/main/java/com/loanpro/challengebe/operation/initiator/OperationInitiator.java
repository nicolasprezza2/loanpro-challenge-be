package com.loanpro.challengebe.operation.initiator;

import com.loanpro.challengebe.operation.Operation;
import com.loanpro.challengebe.operation.OperationRepository;
import com.loanpro.challengebe.operation.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.math.BigDecimal;
import java.util.*;

import static com.loanpro.challengebe.operation.OperationType.*;

@Component
@RequiredArgsConstructor
public class OperationInitiator {

    private final OperationRepository repository;

    private static Map<OperationType, Operation> operations = new HashMap<>();

    @PostConstruct
    public void initiate() {
        Operation addition = new Operation(ADDITION, new BigDecimal(1));
        Operation subtraction = new Operation(SUBTRACTION, new BigDecimal(1));
        Operation multiplication = new Operation(MULTIPLICATION, new BigDecimal(1));
        Operation division = new Operation(DIVISION, new BigDecimal(1));
        Operation squareRoot = new Operation(SQUARE_ROOT, new BigDecimal(2.5));
        Operation randomString = new Operation(RANDOM_STRING, new BigDecimal(2));

        List<Operation> allOps = Arrays.asList(addition, subtraction, multiplication,
                division, squareRoot, randomString);

        List<Operation> savedOperations = repository.saveAll(allOps);
        savedOperations.forEach(op -> {
            operations.put(op.getType(), op);
        });
    }


    public static Operation getByType(OperationType type) {
        return operations.get(type);
    }
}
